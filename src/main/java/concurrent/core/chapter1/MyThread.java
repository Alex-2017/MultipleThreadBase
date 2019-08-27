package concurrent.core.chapter1;

/**
 * 1.2.1 继承Thread类
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("MyThread");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        //同一线程不可多次调用start()方法,否则将会抛出IllegalThreadStateException
        //myThread.start();
        System.out.println("main over");
    }
}
