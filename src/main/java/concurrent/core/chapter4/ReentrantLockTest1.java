package concurrent.core.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.1 使用ReentrantLock实现同步:测试1
 */
public class ReentrantLockTest1 {

    private ReentrantLock lock = new ReentrantLock();

    public void testMethod() {
        //获取锁
        lock.lock();
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " " + (i + 1));
        }
        //释放锁
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockTest1 test = new ReentrantLockTest1();
        Thread threadA = new Thread(test::testMethod, "threadA");
        Thread threadB = new Thread(test::testMethod, "threadB");
        Thread threadC = new Thread(test::testMethod, "threadC");

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
