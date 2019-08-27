package concurrent.core.chapter1;

/**
 * 1.2.4 留意i--与System.out.println()的异常
 */
public class UnSafeThread extends Thread {

    private int count = 5;

    @Override
    public void run() {
        while (count > 0) {
            //注意,本例与UnSafeRunnable的最大不同是count--操作放到了println()方法中执行.
            //虽然println()方法是synchronized,但是count--是在进入该方法之前执行的,所以线程不安全.
            System.out.println(Thread.currentThread().getName() + "'s count is " + count--);
        }
    }

    public static void main(String[] args) {
        UnSafeThread unSafeThread = new UnSafeThread();
        Thread thread1 = new Thread(unSafeThread, "thread1");
        Thread thread2 = new Thread(unSafeThread, "thread2");
        Thread thread3 = new Thread(unSafeThread, "thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
