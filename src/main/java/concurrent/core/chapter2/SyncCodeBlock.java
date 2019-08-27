package concurrent.core.chapter2;

/**
 * 2.2.1 synchronized方法的弊端
 * 2.2.2 synchronized同步代码块的使用
 * 2.2.3 用同步代码块解决同步方法的弊端
 * 2.2.4 一半异步,一半同步
 */
public class SyncCodeBlock {

    //同步方法
    public synchronized void service1() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("index is " + i + ",currentThreadName is " + Thread.currentThread().getName());
        }
    }

    public void service2() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("asynchronized index is " + i + ",currentThreadName is " + Thread.currentThread().getName());
        }
        //同步代码块
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println("synchronized index is " + i + ",currentThreadName is " + Thread.currentThread().getName());
            }
        }

    }

    public static void main(String[] args) {
        SyncCodeBlock syncCodeBlock = new SyncCodeBlock();
        Thread threadA = new Thread(() -> {
            //syncCodeBlock.service1();
            syncCodeBlock.service2();
        }, "threadA");

        Thread threadB = new Thread(() -> {
            //syncCodeBlock.service1();
            syncCodeBlock.service2();
        }, "threadB");

        threadA.start();
        threadB.start();
    }

}
