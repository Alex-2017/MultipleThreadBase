package concurrent.core.chapter4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.13
 * ReentrantLock方法5
 * tryLock()    仅在锁未被线程获取时,才获取该锁定.true表示获取成功,false表示获取失败.
 * tryLock(long timeout, TimeUnit unit) 线程在timeout的时间段中一直尝试获取锁,如果超出时间后仍未获取到,则不再获取.
 */
public class LockMethodTest5 {

    private ReentrantLock lock = new ReentrantLock();

    /**
     * tryLock()方法测试
     */
    public void testTryLock() {
        try {
            if (lock.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " get lock ");
            } else {
                System.out.println(Thread.currentThread().getName() + " fail get lock");
            }
        } finally {
            //如果当前线程获取到了lock,进行解锁.
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void testTryLockWithTime() {
        try {
            System.out.println(Thread.currentThread().getName() + " try get lock at " + System.currentTimeMillis());
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                System.out.println(Thread.currentThread().getName() + " get lock at " + System.currentTimeMillis());
                Thread.sleep(2000);
            } else {
                System.out.println(Thread.currentThread().getName() + " fail get lock at " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release lock at " + System.currentTimeMillis());
            }
        }
    }


    public static void main(String[] args) {
        LockMethodTest5 test = new LockMethodTest5();

        //测试tryLock()
        //Thread threadA = new Thread(test::testTryLock, "threadA");
        //Thread threadB = new Thread(test::testTryLock, "threadB");
        //threadA.start();
        //threadB.start();

        //测试tryLock(long timeout, TimeUnit unit)方法
        Thread threadA = new Thread(test::testTryLockWithTime, "threadA");
        Thread threadB = new Thread(test::testTryLockWithTime, "threadB");
        threadA.start();
        threadB.start();
    }

}
