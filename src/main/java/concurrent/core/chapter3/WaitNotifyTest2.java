package concurrent.core.chapter3;

/**
 * 3.1.3 等待/通知机制的体现
 * 实现一个简易的wait/notify示例
 * 从程序的输出中可以看出,threadA恢复执行是在threadB执行完同步代码块之后,threadA才恢复执行.
 */
public class WaitNotifyTest2 {

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        Thread threadA = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " begin wait at " + System.currentTimeMillis());
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end wait at " + System.currentTimeMillis());
            }
        }, "threadA");

        Thread threadB = new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + " begin notify at " + System.currentTimeMillis());
                object.notify();
                try {
                    //执行notify()方法之后,不会立即释放锁.
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end notify at " + System.currentTimeMillis());
            }
        }, "threadB");

        threadA.start();
        //让threadA先运行进入wait状态
        Thread.sleep(1000);
        threadB.start();
    }
}
