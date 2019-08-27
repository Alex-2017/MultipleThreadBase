package concurrent.core.chapter3;

/**
 * 3.1.4 sleep方法不释放锁
 * wait方法释放锁,sleep方法不释放锁.
 */
public class WaitSleepTest {

    public void testWait(Object lock) {
        synchronized (lock) {
            try {
                System.out.println(Thread.currentThread().getName() + " begin wait at " + System.currentTimeMillis());
                lock.wait();
                System.out.println(Thread.currentThread().getName() + " end wait at " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testSleep(Object lock) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " begin sleep at " + System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end sleep at " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();

        Thread threadA = new Thread(() -> {
            WaitSleepTest test = new WaitSleepTest();
            //test.testWait(lock);
            test.testSleep(lock);
        }, "threadA");

        Thread threadB = new Thread(() -> {
            WaitSleepTest test = new WaitSleepTest();
            //test.testWait(lock);
            test.testSleep(lock);
        }, "threadB");

        threadA.start();
        threadB.start();
    }

}
