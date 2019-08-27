package concurrent.core.chapter3;

/**
 * 3.1.8 方法wait(long)的使用
 */
public class WaitLongTest {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                System.out.println("begin wait");
                try {
                    lock.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end wait");
            }
        });

        thread.start();
    }
}
