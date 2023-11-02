package thread;

public class ThreadRunnable implements Runnable {
    private String message;
    private Integer repeat;
    private Integer delay;

    ThreadRunnable(String message, Integer repeat, Integer delay) {
        this.message = message;
        this.repeat = repeat;
        this.delay = delay;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < repeat; i++) {
            try {
                System.out.print(message);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
