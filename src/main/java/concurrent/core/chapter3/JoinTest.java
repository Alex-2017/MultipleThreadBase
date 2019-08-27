package concurrent.core.chapter3;

/**
 * 3.2.2 用join()方法来解决
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            long time = (long) (Math.random() * 10000);
            try {
                System.out.println(Thread.currentThread().getName() + " begin sleep " + time + "ms");
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + " end sleep " + time + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "joinThread");
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main over");
    }

}
