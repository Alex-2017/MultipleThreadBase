package concurrent.core.chapter2;

/**
 * 2.2.12 多线程的死锁
 */
public class DeadLock implements Runnable {

    private Object lock1 = new Object();

    private Object lock2 = new Object();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if ("threadA".equals(threadName)) {
            synchronized (lock1) {
                System.out.println("thread " + threadName + " get lock1 first");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("thread " + threadName + " get lock2 later");
                }
            }
        } else if ("threadB".equals(threadName)) {
            synchronized (lock2) {
                System.out.println("thread " + threadName + " get lock2 first");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("thread " + threadName + " get lock1 later");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock, "threadA").start();
        new Thread(deadLock, "threadB").start();
    }
}
