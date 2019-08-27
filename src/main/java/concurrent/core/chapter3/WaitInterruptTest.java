package concurrent.core.chapter3;

/**
 * 3.1.5 当interrupt方法遇上wait方法
 */
public class WaitInterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("begin wait");
                    lock.wait();
                    System.out.println("end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        //确保thread进入wait状态
        Thread.sleep(1000);
        thread.interrupt();
    }
}
