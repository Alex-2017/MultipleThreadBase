package concurrent.core.chapter1;

/**
 * 1.3 currentThread()方法
 * 搞清楚线程之间的调用关系,currentThread和this的差异.
 */
public class TestCurrentThread extends Thread {

    public TestCurrentThread() {
        System.out.println("- - -construct init- - -");
        //输出调用此方法的线程,main
        System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
        //输出当前线程的名称,注意,只有在继承Thread时,才能如此调用,实现Runnable则不行.
        System.out.println("this.getName():"+this.getName());
        System.out.println("- - -construct end- - -");
    }

    @Override
    public void run() {
        System.out.println("- - -run init- - -");
        System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
        System.out.println("this.getName():"+this.getName());
        System.out.println("- - -run end- - -");
    }

    public static void main(String[] args) {
        //输出运行main方法的线程名称 main
        System.out.println(Thread.currentThread().getName());

        TestCurrentThread testCurrentThread = new TestCurrentThread();
        //将testCurrentThread放入到Thread中执行
        Thread thread = new Thread(testCurrentThread);
        //System.out.println("- - - - -执行start方法- - - - -");
        //thread.start();

        System.out.println("- - - - -执行run方法- - - - -");
        thread.run();

    }
}
