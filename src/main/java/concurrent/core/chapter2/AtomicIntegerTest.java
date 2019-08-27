package concurrent.core.chapter2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2.3.5 使用原子类进行i++操作
 */
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void run() {
        for(int i=0;i<10000;i++) {
            System.out.println(count.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        AtomicIntegerTest atomic = new AtomicIntegerTest();
        new Thread(atomic).start();
        new Thread(atomic).start();
        new Thread(atomic).start();
        new Thread(atomic).start();
        new Thread(atomic).start();
    }
}
