package concurrent.core.chapter1;

/**
 * 1.11 守护线程
 * 用户线程结束之后,守护线程自动销毁.
 */
public class DaemonThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new DaemonThread());
            //设置线程为守护线程
            thread.setDaemon(true);
            thread.start();
            Thread.sleep(2000);
            System.out.println("main over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
