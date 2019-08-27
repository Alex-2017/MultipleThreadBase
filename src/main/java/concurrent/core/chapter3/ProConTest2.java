package concurrent.core.chapter3;

/**
 * 3.1.11 生产者/消费者模式实现
 * 2.多生产与多消费:操作值-假死.
 * 3.多生产与多消费 操作值
 */
public class ProConTest2 {

    private Object lock = new Object();

    private String value = "";

    public void produce() {
        synchronized (lock) {
            while (!"".equals(value)) {
                System.out.println(Thread.currentThread().getName() + " begin wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end wait");
            }
            System.out.println(Thread.currentThread().getName() + " begin produce");
            value = String.valueOf(System.nanoTime());
            //多个生产线程和消费线程时使用notify会导致假死
            lock.notify();
            //lock.notifyAll();
        }
    }

    public void consume() {
        synchronized (lock) {
            while ("".equals(value)) {
                System.out.println(Thread.currentThread().getName() + " begin wait");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " end wait");
            }
            System.out.println(Thread.currentThread().getName() + " begin consume");
            value = "";
            lock.notify();
            //lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        ProConTest2 test = new ProConTest2();

        Thread[] proThreadArr = new Thread[2];
        Thread[] conThreadArr = new Thread[2];
        for (int i = 0; i < 2; i++) {
            proThreadArr[i] = new Thread(() -> {
                while (true) {
                    test.produce();
                }
            }, "proThread" + (i + 1));

            conThreadArr[i] = new Thread(() -> {
                while (true) {
                    test.consume();
                }
            }, "conThread" + (i + 1));

            proThreadArr[i].start();
            conThreadArr[i].start();
        }
    }
}
