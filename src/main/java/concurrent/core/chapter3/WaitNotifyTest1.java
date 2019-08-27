package concurrent.core.chapter3;

/**
 * 3.1.3 等待/通知机制的体现
 * 如果对象调用wait()或notify()方法时没有在同步方法或同步代码块中,程序会抛出IllegalMonitorStateException
 */
public class WaitNotifyTest1 {
    public static void main(String[] args) {
        Object obj = new Object();
        try {
            obj.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //obj.notify();
    }
}
