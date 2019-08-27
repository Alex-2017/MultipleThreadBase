package concurrent.core.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.12
 * ReentrantLock方法3
 * isFair() 锁是否公平,true->公平,false->不公平.
 * isHeldByCurrentThread() 查询当前线程是否拥有此锁定
 * isLocked()   查询此锁是否被任意线程拥有
 */
public class LockMethodTest3 {
    private ReentrantLock lock = new ReentrantLock();

    public boolean testLockFair(ReentrantLock lock) {
        return lock.isFair();
    }

    public void testIsHeldByCurrentThread() {
        System.out.println("before lock " + lock.isHeldByCurrentThread());
        lock.lock();
        System.out.println("begin lock " + lock.isHeldByCurrentThread());
        lock.unlock();
        System.out.println("end lock " + lock.isHeldByCurrentThread());
    }

    public void testIsLocked() {
        System.out.println("before lock " + lock.isLocked());
        lock.lock();
        System.out.println("begin lock " + lock.isLocked());
        lock.unlock();
        System.out.println("end lock " + lock.isLocked());
    }

    public static void main(String[] args) {
        LockMethodTest3 test = new LockMethodTest3();

        /**
         * 测试isFair()方法
         */
        //ReentrantLock lock = new ReentrantLock();
        ////ReentrantLock默认是非公平锁
        //System.out.println("ReentrantLock default is " + test.testLockFair(lock));

        /**
         * 测试testIsHeldByCurrentThread()方法
         */
        //Thread thread = new Thread(test::testIsHeldByCurrentThread);
        //thread.start();

        /**
         * 测试testIsLocked()方法
         */
        Thread thread = new Thread(test::testIsLocked);
        thread.start();

    }

}
