package concurrent.core.chapter1;

/**
 * 1.4 isAlive()方法  1.6 getId()方法
 * 判断线程是否处于活动状态,当线程处于就绪或者运行状态时,此线程就是活动的.
 * getId()方法的作用是取得线程的唯一标识.
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println("run()->" + this.isAlive());
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        //获取线程id
        System.out.println(testThread.getName() + "'s id is " + testThread.getId());

        System.out.println("before start the thread ->" + testThread.isAlive());
        testThread.start();
        System.out.println("after start the thread ->" + testThread.isAlive());
    }
}
