package concurrency.thread;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadCounterSample {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Runnable r = () -> {
            for (int i = 0; i < 1_000_000; i++) c.syncIncrement();
        };

        List<Thread> treads = Stream.generate(() -> new Thread(r))
                .limit(10).peek(Thread::start)
                .collect(Collectors.toList());

        for (Thread thread : treads) {
            thread.join();
        }

        c.print();
    }
}
