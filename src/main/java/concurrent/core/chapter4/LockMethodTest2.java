package concurrent.core.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.11
 * ReentrantLock方法2
 * hasQueuedThreads():是否有线程在等待获取此lock.
 * hasQueuedThread(thread):判断thread对象是否在等待该锁
 *
 */
public class LockMethodTest2 {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public ReentrantLock getLock() {
        return this.lock;
    }

    public void waitMethod() {
        try {
            lock.lock();
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockMethodTest2 test = new LockMethodTest2();

        Thread threadA = new Thread(test::waitMethod);
        Thread threadB = new Thread(test::waitMethod);

        threadA.start();

        System.out.println("是否有线程在等待获取此lock->"+test.getLock().hasQueuedThreads());
        try {
            //确保threadA执行并进入睡眠状态
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadB.start();

        try {
            //确保threadB开始执行
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadA 是否在等待该锁->"+test.getLock().hasQueuedThread(threadA));
        System.out.println("threadB 是否在等待该锁->"+test.getLock().hasQueuedThread(threadB));
        System.out.println("是否有线程在等待获取此lock->"+test.getLock().hasQueuedThreads());
    }


}
