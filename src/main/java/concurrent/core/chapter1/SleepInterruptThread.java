package concurrent.core.chapter1;

/**
 * 1.7.4 在沉睡中停止
 * 在sleep状态下interrupt线程,抛出InterruptedException.
 */
public class SleepInterruptThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("begin sleep");
            Thread.sleep(20000);
            System.out.println("end sleep");
        } catch (InterruptedException e) {
            //线程的中断标志为false.
            System.out.println("thread was interrupted while sleeping,thread's interrupted state is " + Thread.interrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SleepInterruptThread sleepInterruptThread = new SleepInterruptThread();
            Thread thread = new Thread(sleepInterruptThread);
            thread.start();
            //确保thread进入sleep
            Thread.sleep(1000);
            System.out.println("begin interrupt thread");
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
