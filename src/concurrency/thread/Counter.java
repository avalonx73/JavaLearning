package concurrency.thread;

public class Counter {
    private int x = 0;

    public void increment() {
        this.x++;
    }

    public synchronized void syncIncrement() {
        x++;
    }

    public void print() {
        System.out.println(x);
    }
}
