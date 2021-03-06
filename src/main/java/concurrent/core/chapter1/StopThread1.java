package concurrent.core.chapter1;

/**
 * 1.7.3 能停止的线程-break停止.
 * 通过Thread.interrupted()判断线程状态.如果true,则break.
 * 但是在被break之后仍会输出for循环之后的语句.
 */
public class StopThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            //代码块①
            if (Thread.interrupted()) {
                break;
            }
            //证明线程不是正常执行完,是被interrupt的.
            System.out.println(Thread.currentThread().getName() + i);
        }
        System.out.println("如果不抛出InterruptedException,那么中断线程后仍会输出这句话,说明线程并未正常中断.所以需要采用异常中断法.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread1());
        thread.start();
        try {
            //让线程先运行一会,然后再中断它
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        //为true表明线程已被中断,且还未执行到代码①
        System.out.println(thread.isInterrupted());
        //输出false,表明线程已经通过代码①的判断,并且被移除了中断标志.
        System.out.println(thread.isInterrupted());
    }
}
