package concurrent.core.chapter3;

/**
 * 3.1.6 只通知一个线程
 * 3.1.7 唤醒所有线程
 */
public class WaitNotifyTest3 {

    public void waitMethod(Object lock) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " begin wait");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end wait");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread threadA = new Thread(() -> {
            WaitNotifyTest3 test = new WaitNotifyTest3();
            test.waitMethod(lock);
        }, "threadA");

        Thread threadB = new Thread(() -> {
            WaitNotifyTest3 test = new WaitNotifyTest3();
            test.waitMethod(lock);
        }, "threadB");

        Thread threadC = new Thread(() -> {
            WaitNotifyTest3 test = new WaitNotifyTest3();
            test.waitMethod(lock);
        }, "threadC");

        Thread notifyThread = new Thread(() -> {
            synchronized (lock) {
                //唤醒一个线程
                //lock.notify();
                //唤醒所有线程
                lock.notifyAll();
            }
        }, "notifyThread");

        threadA.start();
        threadB.start();
        threadC.start();

        //确保threadA,B,C都执行了wait方法
        Thread.sleep(1000);

        notifyThread.start();

    }

}
