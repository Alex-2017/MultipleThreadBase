package concurrent.core.chapter2;

/**
 * 2.1.4 synchronized方法与锁对象
 * 第一种情形:methodA加synchronized,methodB不加.两个线程对同一个对象进行操作,methodA和methodB同时被执行.
 * 第二种情形:methodA和methodB都加synchronized,两个线程对同一个对象进行操作,如果threadA先执行,threadB会被阻塞.
 */
public class SynMethod {

    public synchronized void methodA() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin ,time is " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end ,time is " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        try {
            System.out.println(Thread.currentThread().getName() + " begin ,time is " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " end ,time is " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SynMethod synMethod = new SynMethod();

        Thread threadA = new Thread(() -> {
            synMethod.methodA();
        }, "threadA");

        Thread threadB = new Thread(() -> {
            synMethod.methodB();
        }, "threadB");

        threadA.start();

        threadB.start();
    }

}
