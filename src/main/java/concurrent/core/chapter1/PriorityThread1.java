package concurrent.core.chapter1;

/**
 *  1.10.1 线程优先级的继承特性
 */
public class PriorityThread1 {

    public static void main(String[] args) {
        System.out.println("init main thread's priority is " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("after alter,main thread's priority is " + Thread.currentThread().getPriority());
        new Thread(() -> {
            System.out.println("threadA's priority is " + Thread.currentThread().getPriority());
        }).start();

        new Thread(() -> {
            System.out.println("threadB's priority is " + Thread.currentThread().getPriority());
        }).start();

    }

}
