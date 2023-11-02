package threadpool;

import java.util.Random;

public class Counter {

    public Double count(double a) {
        int param = (int) a;
        int timeout = new Random().nextInt(60 - 10 - 1) + 10;

        System.out.println("Start count = " + param);

        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException ignored) {
        }

/*      for (int i = 0; i < 10_000_000; i++) {
            a = a + Math.tan(a);
        } */
        System.out.println("End count = " + param);
        return a;
    }
}
