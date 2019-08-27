package concurrent.core.chapter1;

/**
 * 1.7.8 使用return停止线程
 */
public class InterruptReturn implements Runnable {

    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("interrupted! end!");
                return;
            }
            System.out.println(System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        try {
            InterruptReturn interruptReturn = new InterruptReturn();
            Thread thread = new Thread(interruptReturn);
            thread.start();
            Thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
