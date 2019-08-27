package concurrent.core.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2.3.6 原子类也并不完全安全
 * addCount()未添加synchronized时,线程不安全.
 * 添加synchronized后,控制线程安全.
 */
public class AtomicIntegerUnSafe implements Runnable{

    private AtomicInteger count = new AtomicInteger(0);

    public synchronized void addCount() {
        System.out.println(count.addAndGet(100));
        count.addAndGet(1);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        AtomicIntegerUnSafe unSafe = new AtomicIntegerUnSafe();
        new Thread(unSafe).start();
        new Thread(unSafe).start();
        new Thread(unSafe).start();
        new Thread(unSafe).start();
        new Thread(unSafe).start();
    }
}
