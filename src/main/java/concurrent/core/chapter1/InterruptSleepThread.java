package concurrent.core.chapter1;

/**
 * 1.7.4 在沉睡中停止
 * 先interrupt线程,随后进行sleep操作,抛出InterruptedException.
 */
public class InterruptSleepThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {

        }
        try {
            System.out.println("begin sleep");
            Thread.sleep(5000);
            System.out.println("end sleep");
        } catch (InterruptedException e) {
            //线程中断状态为false
            System.out.println("interrupt thread first,and then sleep the thread.what will happen? thread's interrupted state is " + Thread.interrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new InterruptSleepThread());
        thread.start();
        System.out.println("begin interrupt thread");
        thread.interrupt();
    }

}
