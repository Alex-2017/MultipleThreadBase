package concurrent.core.chapter2;

/**
 * 2.3.3 解决异步死循环
 * 2.3.7 synchronized 代码块有volatile同步的功能
 */
public class RunThread extends Thread {

    //如果不添加volatile,线程不会被停止.
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        System.out.println("进入run()方法");
        while (isRunning) {
            //synchronized也具有可见性.
            //synchronized ("123") {
            //
            //}
        }
        System.out.println("线程被停止了");
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public static void main(String[] args) {
        RunThread thread = new RunThread();
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.setRunning(false);
        System.out.println("已经赋值为false");
    }
}
