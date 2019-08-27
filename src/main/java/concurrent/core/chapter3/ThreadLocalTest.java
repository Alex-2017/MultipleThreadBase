package concurrent.core.chapter3;

/**
 * 3.3.2 验证线程变量的隔离性
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread threadA = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " first get threadLocal value is " + threadLocal.get());
            threadLocal.set("threadA");
            System.out.println(Thread.currentThread().getName() + " after set threadLocal, the value is " + threadLocal.get());
        }, "threadA");

        Thread threadB = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " first get threadLocal value is " + threadLocal.get());
            threadLocal.set("threadB");
            System.out.println(Thread.currentThread().getName() + " after set threadLocal, the value is " + threadLocal.get());
        }, "threadB");

        System.out.println(Thread.currentThread().getName() + " first get threadLocal value is " + threadLocal.get());
        threadLocal.set("main");
        System.out.println(Thread.currentThread().getName() + " after set threadLocal, the value is " + threadLocal.get());

        threadA.start();
        threadB.start();
    }
}
