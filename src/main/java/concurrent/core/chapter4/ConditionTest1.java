package concurrent.core.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.4 正确使用Condition实现等待/通知
 */
public class ConditionTest1 {

    private ReentrantLock lock = new ReentrantLock();

    //condition的await(),sign()等方法需要在lock.lock()和lock.unlock()之间.
    private Condition condition = lock.newCondition();

    public void await() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin await at " + System.currentTimeMillis());
            //等待
            condition.await();
            System.out.println(Thread.currentThread().getName() + " end await at " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " begin signal at " + System.currentTimeMillis());
            //通知
            condition.signal();
            //证明condition不会立即释放锁
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end signal at " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest1 test = new ConditionTest1();

        Thread awaitThread = new Thread(test::await, "awaitThread");
        Thread signalThread = new Thread(test::signal, "signalThread");

        awaitThread.start();
        //确保awaitThread先进入await
        Thread.sleep(3000);

        signalThread.start();
    }
}
