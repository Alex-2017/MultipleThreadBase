package concurrent.core.chapter2;

/**
 * 2.2.9 静态同步synchronized方法与synchronized(class)代码块
 */
public class SyncClass {

    public synchronized static void service1() {
        try {
            System.out.println("thread name is "+Thread.currentThread().getName()+",在"+System.currentTimeMillis()+"进入service1方法");
            Thread.sleep(3000);
            System.out.println("thread name is "+Thread.currentThread().getName()+",在"+System.currentTimeMillis()+"退出service1方法");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void service2() {
        synchronized (SyncClass.class) {
            System.out.println("thread name is "+Thread.currentThread().getName()+",在"+System.currentTimeMillis()+"进入service2方法");
            System.out.println("thread name is "+Thread.currentThread().getName()+",在"+System.currentTimeMillis()+"退出service2方法");
        }
    }

    public static void main(String[] args) {
        //以Class对象作为锁,public synchronized static和synchronized (SyncClass.class)作用相同
        //Thread threadA = new Thread(() -> {
        //    SyncClass.service1();
        //},"threadA");
        //
        //Thread threadB = new Thread(() -> {
        //    SyncClass.service2();
        //},"threadB");

        //Class锁可以对类的所有对象实例起作用
        Thread threadA = new Thread(() -> {
            SyncClass syncClass = new SyncClass();
            syncClass.service1();
        },"threadA");

        Thread threadB = new Thread(() -> {
            SyncClass syncClass = new SyncClass();
            syncClass.service2();
        },"threadB");

        threadA.start();
        threadB.start();

    }



}
