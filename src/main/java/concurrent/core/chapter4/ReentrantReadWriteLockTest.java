package concurrent.core.chapter4;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 4.2.1
 * 4.2.2
 * 4.2.3
 * 4.2.4
 */
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //读读共享
    public void readAndRead() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " get read lock at " + System.currentTimeMillis());
            //sleep5000ms,查看下个线程获取时间是否是sleep后的时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void writeAndWrite() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " get write lock at " + System.currentTimeMillis());
            //sleep5000ms,查看下个线程获取时间是否是sleep后的时间
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        //读读共享
        //Thread threadA = new Thread(test::readAndRead, "threadA");
        //Thread threadB = new Thread(test::readAndRead, "threadB");
        //threadA.start();
        //threadB.start();

        //写写互斥
        //Thread threadA = new Thread(test::writeAndWrite, "threadA");
        //Thread threadB = new Thread(test::writeAndWrite, "threadB");
        //threadA.start();
        //threadB.start();

        //读写互斥||写读互斥
        Thread readThread = new Thread(test::readAndRead, "readThread");
        Thread writeThread = new Thread(test::writeAndWrite, "writeThread");

        readThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeThread.start();
    }

}
