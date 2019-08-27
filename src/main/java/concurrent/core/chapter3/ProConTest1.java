package concurrent.core.chapter3;

/**
 * 3.1.11 生产者/消费者模式实现
 * 1.一生产一消费
 */
public class ProConTest1 {

    private String value = "";

    private final Object lock = new Object();

    //生产方法
    public void produce() {
        synchronized (lock) {
            while (!"".equals(value)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value = String.valueOf(System.nanoTime());
            System.out.println(Thread.currentThread().getName() + " produce " + value);
            lock.notify();
        }
    }

    //消费方法
    public void consume() {
        synchronized (lock) {
            while ("".equals(value)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " consume " + value);
            value = "";
            lock.notify();
        }
    }

    public static void main(String[] args) {
        ProConTest1 test = new ProConTest1();

        Thread proThread = new Thread(() -> {
            while (true) {
                test.produce();
            }
        }, "proThread");
        Thread conThread = new Thread(() -> {
            while (true) {
                test.consume();
            }
        }, "conThread");

        proThread.start();
        conThread.start();
    }

}
