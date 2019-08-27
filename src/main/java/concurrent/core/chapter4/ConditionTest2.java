package concurrent.core.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.6 使用多个Condition实现通知部分线程
 */
public class ConditionTest2 {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition conditionA = reentrantLock.newCondition();
    private Condition conditionB = reentrantLock.newCondition();

    public void awaitA() {
        try {
            System.out.println("awaitA begin at " + System.currentTimeMillis());
            reentrantLock.lock();
            conditionA.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("awaitB end at " + System.currentTimeMillis());
    }

    public void awaitB() {
        try {
            System.out.println("awaitB begin at " + System.currentTimeMillis());
            reentrantLock.lock();
            conditionB.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println("awaitB end at " + System.currentTimeMillis());
    }

    public void signA() {
        try {
            System.out.println("signA begin at " + System.currentTimeMillis());
            reentrantLock.lock();
            conditionA.signal();
            System.out.println("signA end at " + System.currentTimeMillis());
        }finally {
            reentrantLock.unlock();
        }
    }

    public void signB() {
        try {
            System.out.println("signB begin at " + System.currentTimeMillis());
            reentrantLock.lock();
            conditionB.signal();
            System.out.println("signB end at " + System.currentTimeMillis());
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionTest2 test2 = new ConditionTest2();

        Thread threadA = new Thread(test2::awaitA);
        Thread threadB = new Thread(test2::awaitB);

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test2.signB();
    }
}
