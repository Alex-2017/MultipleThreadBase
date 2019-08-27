package concurrent.core.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.10
 * ReentrantLock方法1
 * getHoldCount():  获取当前ReentrantLock对象持有锁的个数(不包括已释放的锁)
 * getQueueLength():    获取等待获取该锁的线程数
 * hasWaiters(condition):   判断condition是否有等待线程
 * getWaitQueueLength(condition):   获取Condition对象相关的等待线程数.
 */
public class LockMethodTest1 {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public ReentrantLock getLock() {
        return lock;
    }

    public void testCount() {
        lock.lock();
        //持有一个锁,holdCount为1.
        System.out.println("holdCount->" + lock.getHoldCount());
        lock.unlock();
        //释放锁,holdCount为0.
        System.out.println("holdCount->" + lock.getHoldCount());

        lock.lock();
        lock.lock();
        //持有两个锁,holdCount为2.
        System.out.println("holdCount->" + lock.getHoldCount());
        lock.unlock();
        //释放掉一个,holdCount为1.
        System.out.println("holdCount->" + lock.getHoldCount());
        //两个都释放,holdCount为0.
        lock.unlock();
        System.out.println("holdCount->" + lock.getHoldCount());
    }

    public void testGetQueueLength() {
        lock.lock();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.unlock();
    }

    public void aWait() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            //判断condition是否有等待线程
            if (lock.hasWaiters(condition)) {
                //hasWaiters(condition),getWaitQueueLength(condition)方法只可在获得锁的时候能够调用.
                System.out.println("before signal,await threads is " + lock.getWaitQueueLength(condition));
                condition.signal();
                System.out.println("after signal,await threads is " + lock.getWaitQueueLength(condition));
            }
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        LockMethodTest1 test = new LockMethodTest1();

        /**
         * getHoldCount()测试
         */
        //Thread thread = new Thread(test::testCount);
        //thread.start();

        /**
         * getQueueLength()测试
         */
        //for (int i = 0; i < 5; i++) {
        //    new Thread(test::testGetQueueLength).start();
        //}
        //
        //try {
        //    //等待2s,确保所有线程均已start.
        //    Thread.sleep(2000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //System.out.println("queueLength is " + test.getLock().getQueueLength());

        /**
         * getWaitQueueLength(condition)测试
         */
        for (int i = 0; i < 5; i++) {
            new Thread(test::aWait).start();
        }

        try {
            //确保5个线程启动完毕
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //唤醒一个线程
        test.signal();
    }

}
