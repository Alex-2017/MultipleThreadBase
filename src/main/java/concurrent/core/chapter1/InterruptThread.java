package concurrent.core.chapter1;

/**
 * 1.7.1 停止不了的线程
 * 调用interrupt()方法并不能真正的停止线程
 */
public class InterruptThread extends Thread {

    @Override
    public void run() {
        while (true) {

        }
    }

    public static void main(String[] args) {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();

        try {
            //在进行中断操作之前,让interruptThread运行1s
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //执行中断操作之后,通过控制台的运行状态来看,线程并未停止运行
        interruptThread.interrupt();
    }
}
