package concurrent.core.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.9 公平锁与非公平锁
 * 即便是公平锁,线程开始运行
 */
public class FairLockTest {

    private ReentrantLock fairLock = new ReentrantLock(true);

    private ReentrantLock unFairLock = new ReentrantLock(false);

    public void testFairLock() {
        fairLock.lock();
        System.out.println(Thread.currentThread().getName() + " get fair lock ");
        fairLock.unlock();
    }

    public void testUnFairLock() {
        unFairLock.lock();
        System.out.println(Thread.currentThread().getName() + " get unfair lock");
        unFairLock.unlock();
    }


    public static void main(String[] args) {
        FairLockTest test = new FairLockTest();
        Thread[] threadArr = new Thread[5];

        for (int i = 0; i < 5; i++) {
            String threadName = "Thread" + (i + 1);
            threadArr[i] = new Thread(() -> {
                //(...begin run)语句也不一定和testFairLock()中获取锁的语句顺序一一对应.因为一个是非同步方法,另一个是同步方法.
                System.out.println(Thread.currentThread().getName() + " begin run");
                //test.testFairLock();
                test.testUnFairLock();
            }, threadName);
        }

        for (int i = 0; i < 5; i++) {
            threadArr[i].start();
        }
    }

}
