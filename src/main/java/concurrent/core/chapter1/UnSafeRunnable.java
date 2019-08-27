package concurrent.core.chapter1;

/**
 * 1.2.3 实例变量与线程安全
 * 示例1
 */
public class UnSafeRunnable implements Runnable {

    private int count = 5;

    @Override
    public synchronized void run() {
        while (count > 0) {
            count--;
            System.out.println(Thread.currentThread().getName() + "'count is " + count);
        }
    }

    public static void main(String[] args) {
        //每个线程使用各自的变量,运行正常.
        //new Thread(new UnSafeRunnable(), "threadA").start();
        //new Thread(new UnSafeRunnable(), "threadB").start();
        //new Thread(new UnSafeRunnable(), "threadC").start();

        //每个线程使用相同变量时,输出的数字顺序不再一致.可通过给run方法添加synchronized.
        UnSafeRunnable unSafeRunnable = new UnSafeRunnable();
        new Thread(unSafeRunnable,"threadA").start();
        new Thread(unSafeRunnable,"threadB").start();
        new Thread(unSafeRunnable,"threadC").start();
    }
}
