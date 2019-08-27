package concurrent.core.chapter4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 4.1.16 使用Condition实现顺序执行
 */
public class ConditionTest3 {

    private static volatile int nextPrintWho = 1;

    private static ReentrantLock lock = new ReentrantLock();

    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 1) {
                    //线程在conditionA上等待
                    conditionA.await();
                }
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                nextPrintWho = 2;
                //唤醒conditionB所有等待的线程.
                conditionB.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadB = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 2) {
                    //线程在conditionB上等待
                    conditionB.await();
                }
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                nextPrintWho = 3;
                //唤醒conditionC所有等待的线程.
                conditionC.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread threadC = new Thread(() -> {
            try {
                lock.lock();
                while (nextPrintWho != 3) {
                    //线程在conditionC上等待
                    conditionC.await();
                }
                for (int i = 1; i <= 3; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                nextPrintWho = 1;
                //唤醒conditionA所有等待的线程.
                conditionA.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread[] aThrArr = new Thread[5];
        Thread[] bThrArr = new Thread[5];
        Thread[] cThrArr = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aThrArr[i] = new Thread(threadA, "threadA");
            bThrArr[i] = new Thread(threadB, "threadB");
            cThrArr[i] = new Thread(threadC, "threadC");
        }
        for (int i = 0; i < 5; i++) {
            aThrArr[i].start();
            bThrArr[i].start();
            cThrArr[i].start();
        }
    }
}
