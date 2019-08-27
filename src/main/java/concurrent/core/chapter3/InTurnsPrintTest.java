package concurrent.core.chapter3;

/**
 * 3.1.14 实战:等待/通知之交叉备份
 */
public class InTurnsPrintTest {

    //注意:此处不管加没加volatile关键字,程序始终正常执行.对volatile的使用场景仍有疑问.
    private volatile boolean preIsA = false;

    //同步方法
    public synchronized void backupA() {
        while (preIsA == false) {
            try {
                //以此对象为锁,调用其wait方法
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " backup A");
        preIsA = false;
        //通知所有wait线程,避免假死.
        notifyAll();
    }

    public synchronized void backupB() {
        while (preIsA == true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " backup B");
        preIsA = true;
        notifyAll();
    }

    public static void main(String[] args) {
        InTurnsPrintTest test = new InTurnsPrintTest();
        Thread[] aArr = new Thread[10];
        Thread[] bArr = new Thread[10];
        for (int i = 0; i < 10; i++) {
            String aThreadName = "ThreadA" + (i + 1);
            aArr[i] = new Thread(test::backupA, aThreadName);
            String bThreadName = "ThreadB" + (i + 1);
            bArr[i] = new Thread(test::backupB, bThreadName);
        }
        for (int i = 0; i < 10; i++) {
            aArr[i].start();
            bArr[i].start();
        }
    }

}
