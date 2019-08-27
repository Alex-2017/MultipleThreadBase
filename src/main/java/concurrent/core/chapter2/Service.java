package concurrent.core.chapter2;

/**
 * 2.1.6 synchronized锁重入
 * 当有一条线程已经获得了某个对象的锁,在锁没释放的情况下,该线程还可继续获得该对象的锁.
 */
public class Service {

    public synchronized void service1() {
        System.out.println("service1");
        //在已经获得锁的情况下,仍可继续调用同步方法
        service2();
    }

    public synchronized void service2() {
        System.out.println("service2");
        //在已经获得锁的情况下,仍可继续调用同步方法
        service3();
    }

    public synchronized void service3() {
        System.out.println("service3");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Service service = new Service();
            service.service1();
        });
        thread.start();
    }

}
