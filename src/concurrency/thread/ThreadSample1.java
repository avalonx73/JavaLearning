package concurrency.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadSample1 {
    public static void main(String[] args) throws InterruptedException {

        //Container container = new Container();
        Runnable foo = () -> {
            Container container = new Container();
            for (int i=0; i < 100000; i++) {
                container.addToSyncList("foo");
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int count =0; count <10; count++){
            Thread thread = new Thread(foo);
            System.out.println(thread);
            thread.start();
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.join();
            System.out.println(Container.sizeSyncList());
        }
        System.out.println(Container.sizeSyncList());
    }
}
