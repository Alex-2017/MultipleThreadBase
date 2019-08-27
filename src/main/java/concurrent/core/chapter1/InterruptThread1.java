package concurrent.core.chapter1;

/**
 * 1.7.2 判断线程是否是停止状态
 */
public class InterruptThread1 {

    public static void main(String[] args) {
        //获取main线程
        Thread mainThread = Thread.currentThread();
        //中断
        mainThread.interrupt();

        //使用mainThread.isInterrupted()获取中断状态,两次的中断状态都不会变.
        System.out.println("first use isInterrupted() get interrupted state -> " + mainThread.isInterrupted());
        System.out.println("second use isInterrupted() get interrupted state -> " + mainThread.isInterrupted());

        //使用Thread.interrupted()获取中断状态
        System.out.println("first use interrupted() get interrupted state -> "+Thread.interrupted());
        //第二次输出时,中断状态被清空了
        System.out.println("second use interrupted() get interrupted state -> "+Thread.interrupted());
    }

}
