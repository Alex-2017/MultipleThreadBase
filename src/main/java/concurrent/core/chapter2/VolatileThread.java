package concurrent.core.chapter2;

/**
 * 2.3.4 volatile非原子的特性
 */
public class VolatileThread extends Thread {

    private volatile static int count = 0;

    //添加synchronized可解决线程安全问题
    private synchronized static void addCount() {
        for (int i = 0; i < 100; i++) {
            count++;
        }
        System.out.println("count=" + count);
    }

    @Override
    public void run() {
        addCount();
    }

    public static void main(String[] args) {
        VolatileThread[] volatileThreads = new VolatileThread[100];
        for (int i = 0; i < 100; i++) {
            volatileThreads[i] = new VolatileThread();
            volatileThreads[i].start();
        }
    }
}
