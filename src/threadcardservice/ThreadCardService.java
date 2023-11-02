package threadcardservice;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.lmax.disruptor.util.Util.ceilingNextPowerOfTwo;

public final class ThreadCardService {

    public static final int PACKAGE_SIZE = 1_000;

    private final Semaphore semaphore;

    private final Disruptor<Event>[] disruptors;
    private final RingBuffer<Event>[] ringBuffers;

    private final int threadCount;
    private final int queueSize;

    private Consumer eventConsumer;

    private final ConcurrentMap<String, Integer> personMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<Integer, HashSet<String>> indexMap = new ConcurrentHashMap<>();

    private Thread ownerThread;

    private final AtomicBoolean isRunning = new AtomicBoolean(true);

    private final Thread monitorThread;
    private final boolean isMonitored;

    public ThreadCardService() {
        isMonitored = false;

        threadCount = Runtime.getRuntime().availableProcessors() * 2;
        queueSize = ceilingNextPowerOfTwo(PACKAGE_SIZE / threadCount);

        System.out.println("Use " + threadCount + " threads, queueSize = " + queueSize);

        semaphore = new Semaphore(threadCount);
        disruptors = new Disruptor[threadCount];
        ringBuffers = new RingBuffer[threadCount];
        NameThreadFactory nameThreadFactory = new NameThreadFactory(getClass().getSimpleName() + "-Events");
        for (int i = 0; i < threadCount; i++) {
            disruptors[i] = new Disruptor<>(
                    Event::new,
                    queueSize,
                    nameThreadFactory.setName(getClass().getSimpleName() + "-Events-" + i),
                    ProducerType.SINGLE,
                    new BlockingWaitStrategy());
            disruptors[i].handleEventsWith(this::handleEvent);
            ringBuffers[i] = disruptors[i].start();
        }

        if (isMonitored) {
            monitorThread = new Thread(getClass().getSimpleName() + "-Monitoring") {
                @Override
                public void run() {
                    while (isRunning.get()) {
                        try {
                            TimeUnit.MINUTES.sleep(10);
                        } catch (InterruptedException ignored) {
                        }

                        int size = 0;
                        for (HashSet<String> set : indexMap.values()) {
                            size = size + set.size();
                        }

                        //log.info("Monitoring: personMap size = {}, indexMap size = {}", personMap.size(), size);
                    }
                }
            };

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    shutdown();
                } catch (Exception e) {
                    //  log.error(e.toString(), e);
                }
            }));

            monitorThread.start();
        } else {
            monitorThread = null;
        }
    }

    public void begin(Consumer eventConsumer) {
        personMap.clear();
        indexMap.clear();

        ownerThread = Thread.currentThread();

        this.eventConsumer = eventConsumer;
    }

    public void publish(String batchId, PersonXml personXml, int position, Set<String> keys) {
        int index = getIndex(keys);

//        log.debug("Index: {}", index);

        long sequence = ringBuffers[index].next();
        Event event = ringBuffers[index].get(sequence);
        event.batchId = batchId;
        event.personXml = personXml;
        event.position = position;
        event.index = index;
        event.keys = keys;
        event.isBatchCompleted = false;

        ringBuffers[index].publish(sequence);
    }

    public void await() {
        if (!Thread.currentThread().equals(ownerThread)) {
            throw new IllegalStateException("Not owner thread");
        }

        semaphore.drainPermits();

        for (RingBuffer<Event> ringBuffer : ringBuffers) {
            long sequence = ringBuffer.next();
            Event event = ringBuffer.get(sequence);
            event.batchId = null;
            event.personXml = null;
            event.position = -1;
            event.index = -1;
            event.keys = null;
            event.isBatchCompleted = true;

            ringBuffer.publish(sequence);
        }

        try {
            semaphore.acquire(threadCount);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            personMap.clear();
            indexMap.clear();
        }

//        log.debug("Await end");
    }

    public int findColdQueue() {
        long remainingCapacity = 0;
        int idx = 0;

        for (int i = 0; i < ringBuffers.length; i++) {
            if (ringBuffers[i].remainingCapacity() == queueSize) {
                idx = i;

                break;
            }

            if (ringBuffers[i].remainingCapacity() > remainingCapacity) {
                remainingCapacity = ringBuffers[i].remainingCapacity();
                idx = i;
            }
        }

//        log.debug("Find cold queue index: {}", idx);

        return idx;
    }

    private void handleEvent(Event event, long sequence, boolean endOfBatch) {
        if (event.personXml != null) {
       //     System.out.println("Handle: " + event.personXml.getRnk());
        } else {
      //      System.out.println("Handle: event.personXml = null");
        }

        try {
            if (event.isBatchCompleted) {
//                log.debug("END");

                semaphore.release();
            } else {
                try {
                    eventConsumer.accept(event.personXml, event.batchId, event.position);
                } finally {
                    deleteIndex(event.index, event.keys);
                }
            }
        } catch (Throwable throwable) {
            //  log.error(throwable.toString(), throwable);
        } finally {
            event.batchId = null;
            event.personXml = null;
            event.position = -1;
            event.index = -1;
            event.keys = null;
            event.isBatchCompleted = false;
        }
    }

    public void shutdown() {
        //  log.info("Shutdown begin ...");

        isRunning.set(false);

        if (isMonitored) {
            try {
                monitorThread.interrupt();
            } catch (Exception e) {
                //  log.error(e.toString(), e);
            }
        }

        try {
            for (Disruptor<Event> disruptor : disruptors) {
                disruptor.shutdown();
            }
        } catch (Exception e) {
            //  log.error(e.toString(), e);
        }

        // log.info("Shutdown end");
    }

    private int getIndex(Set<String> keys) {
        int index = findIndex(keys);

        updateIndex(index, keys);

        return index;
    }

    private int findIndex(Set<String> keys) {
        Integer index;
        for (String key : keys) {
            index = personMap.get(key);
            if (index != null) {
                return index;
            }
        }

        return findColdQueue();
    }

    private void updateIndex(Integer index, Set<String> keys) {
        for (String key : keys) {
            updateIndex(index, key);
        }
    }

    private void updateIndex(Integer index, String key) {
        personMap.put(key, index);
        indexMap.computeIfAbsent(index, k -> new HashSet<>()).add(key);
    }

    private void deleteIndex(Integer index, Set<String> keys) {
        for (String key : keys) {
            deleteIndex(index, key);
        }
    }

    private void deleteIndex(Integer index, String key) {
        personMap.remove(key, index);
        final HashSet<String> set = indexMap.get(index);
        if (set != null) {
            set.remove(key);
        }

        indexMap.computeIfAbsent(index, k -> new HashSet<>()).add(key);
    }

    static class Event {

        boolean isBatchCompleted;

        int position;

        int index;

        Set<String> keys;

        String batchId;

        PersonXml personXml;

        public int getPosition() {
            return position;
        }

        @Override
        public String toString() {
            if (personXml != null) {
                return "Event{" +
                        "personXml=" + personXml.getRnk() +
                        "}";
            } else {
                return "Event{personXml=null}";
            }
        }
    }

    @FunctionalInterface
    public interface Consumer {

        void accept(PersonXml personXml, String batchId, int position) throws InterruptedException;
    }
}
