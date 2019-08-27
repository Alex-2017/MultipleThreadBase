package concurrent.core.chapter4;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.14 方法awaitUninterruptibly的使用
 * 4.1.15 方法awaitUnit()的使用
 */
public class AwaitUninterruptiblyTest {

    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    //await后被interrupt会抛出异常,释放锁.
    public void await() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin wait ");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " end wait ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //awaitUninterruptibly后的线程在进入await之后,不会被interrupt影响,正常执行.
    public void awaitUninterruptibly() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin wait ");
            condition.awaitUninterruptibly();
            System.out.println(Thread.currentThread().getName() + " end wait ");
        } finally {
            lock.unlock();
        }
    }

    //awaitUnit()的使用
    public void awaitUntil() {
        try {
            lock.lock();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, 2);
            //如果在指定时间之内未被唤醒,将会自动苏醒.
            System.out.println(Thread.currentThread().getName() + " begin wait at " + System.currentTimeMillis());
            condition.awaitUntil(calendar.getTime());
            System.out.println(Thread.currentThread().getName() + " end wait at " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void signal() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + " begin signal at " + System.currentTimeMillis());
        condition.signal();
        System.out.println(Thread.currentThread().getName() + " end signal at " + System.currentTimeMillis());
        lock.unlock();
    }

    public static void main(String[] args) {
        AwaitUninterruptiblyTest test = new AwaitUninterruptiblyTest();

        /**
         * 4.1.14测试代码
         */
        //Thread waitThread = new Thread(test::await,"waitThread");
        //Thread waitThread = new Thread(test::awaitUninterruptibly, "waitThread");
        //Thread notifyThread = new Thread(test::signal, "notifyThread");
        //
        //waitThread.start();
        //
        //try {
        //    Thread.sleep(1000);
        //    waitThread.interrupt();
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //
        //notifyThread.start();

        /**
         * 4.1.15测试代码
         */
        Thread awaitThread = new Thread(test::awaitUntil, "awaitThread");
        Thread notifyThread = new Thread(test::signal, "signalThread");

        awaitThread.start();

        try {
            //一秒之后唤醒线程,小于苏醒时间2s,被notifyThread唤醒.
            //Thread.sleep(1000);
            //三秒之后唤醒线程,大于苏醒时间2s,自动唤醒.
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notifyThread.start();
    }


}
