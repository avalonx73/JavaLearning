package thread;

import java.util.Optional;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.MIN_PRIORITY;

public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Optional<String> optionalString = Optional.empty();
        String s = optionalString.get();

        ThreadRunnable threadRunnable1 = new ThreadRunnable(">", 60, 300);
        ThreadRunnable threadRunnable2 = new ThreadRunnable("~", 60, 300);

        Thread thread1 = new Thread(threadRunnable1, "Thread1");
        Thread thread2 = new Thread(threadRunnable2, "Thread2");

        thread1.start();
        thread2.start();
        System.out.print("E");
    }
}
