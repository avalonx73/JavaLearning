package threadcardservice;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private String name;

    public NameThreadFactory(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public NameThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = name + "-" + poolNumber.getAndIncrement() + "-";
    }

    public NameThreadFactory setName(String name) {
        this.name = name;

        return this;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, name == null ? namePrefix + threadNumber.getAndIncrement() : name, 0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);

        return t;
    }
}
