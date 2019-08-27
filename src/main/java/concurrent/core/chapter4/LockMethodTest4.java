package concurrent.core.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.13
 * ReentrantLock方法4
 * lockInterruptibly()  如果当前线程未被interrupt,则获取lock.如果已被interrupt,则抛出InterruptedException
 */
public class LockMethodTest4 extends Thread {

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        //testLock();
        testLockInterruptibly();
    }

    public void testLock() {
        //确保线程在被lock之前被interrupt
        System.out.println("isInterrupted -> " + this.isInterrupted());
        System.out.println(Thread.currentThread().getName() + " before get lock");
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " get lock");
        lock.unlock();
        System.out.println(Thread.currentThread().getName() + " release lock");
    }

    public void testLockInterruptibly() {
        //确保线程在被lock之前被interrupt
        System.out.println("isInterrupted -> " + this.isInterrupted());
        System.out.println(Thread.currentThread().getName() + " before get lock");
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " get lock");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " run into error");
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock");
        }
    }

    public static void main(String[] args) {
        LockMethodTest4 thread = new LockMethodTest4();
        thread.start();
        thread.interrupt();
    }

}
