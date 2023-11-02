package threadpool;

import org.openjdk.jol.info.GraphLayout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultithreadClient {
    public static void main(String[] args) throws Exception {

        int size = 3;
        Map<String, String> map = new ConcurrentHashMap<>(size);

        for (int i = 0; i < size; i++) {
            map.put(String.valueOf(i), "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }
        // Оцениваем размер экземпляра HashMap в байтах
        long totalSize = GraphLayout.parseInstance(map).totalSize();
        System.out.println("Size of HashMap: " + totalSize + " bytes");

        // ExecutorService threadPool = Executors.newFixedThreadPool(1);

        // SynchronousQueue<Runnable> syncQueue = new SynchronousQueue<>();

        // LinkedBlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(1);

        // ExecutorService threadPool = Executors.newCachedThreadPool();

/*        ExecutorService threadPool = new ThreadPoolExecutor(0,
                1,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());*/

/*        ExecutorService threadPool = new ThreadPoolExecutor(0,
                1,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1),
                new NameThreadFactory("XXX"),
                new ThreadPoolExecutor.AbortPolicy());*/


        CardDuplicateService service = new CardDuplicateService();

        service.findDeep(new Card("3", "3"));

/*        Counter counter = new Counter();

        Map<Integer, CompletableFuture<Double>> futures = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            final int j = i;
            futures.put(i,
                    CompletableFuture.supplyAsync(
                            () -> counter.count(j),
                            threadPool
                    ));
        }

        int activeCount = ((ThreadPoolExecutor) threadPool).getActiveCount();

        long start = System.nanoTime();

        double value = 0;
        double count = 0;
        for (Map.Entry<Integer, CompletableFuture<Double>> future : futures.entrySet()) {
            long start1 = System.nanoTime();
            future.getValue().join();
          //  count = future.getValue().get();
            value += count;
            System.out.println(format("Executed %d by %d s, value : %f",
                    future.getKey(),
                    (System.nanoTime() - start1) / (1000_000),
                    value));
        }

        System.out.println("--------------------------------------");
        System.out.println(format("All Executed by %d s, value : %f",
                (System.nanoTime() - start) / (1000_000),
                value));

        threadPool.shutdown();*/
    }
}
