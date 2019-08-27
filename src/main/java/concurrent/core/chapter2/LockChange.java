package concurrent.core.chapter2;

/**
 * 2.2.16 锁对象的改变
 * 以String常量为锁和以对象为锁时
 */
public class LockChange {

    private String strLock = "LockA";

    private UserInfo userInfoLock = new UserInfo("Alex");

    public void service1() {
        synchronized (strLock) {
            System.out.println("thread " + Thread.currentThread().getName() + " begin run service1() at " + System.currentTimeMillis());
            strLock = "LockB";
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread " + Thread.currentThread().getName() + " end run service1() at " + System.currentTimeMillis());
        }
    }

    public void service2() {
        synchronized (userInfoLock) {
            System.out.println("thread " + Thread.currentThread().getName() + " begin run service2() at " + System.currentTimeMillis());
            userInfoLock.setUsername("BaseC");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread " + Thread.currentThread().getName() + " end run service2() at " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {

        LockChange lockChange = new LockChange();

        /**
         * 以String常量为lock时
         */
        //Thread threadA = new Thread(() -> {
        //    lockChange.service1();
        //}, "threadA");
        //
        //Thread threadB = new Thread(() -> {
        //    lockChange.service1();
        //}, "threadB");
        //
        ////如果俩线程直接启动,threadA和threadB极有可能拿的锁都是"LockA",同步执行.
        //threadA.start();
        //try {
        //    //让threadA先运行,确保其改变了strLock,这样threadB启动时获取的锁就变成了"LockB",异步执行.
        //    Thread.sleep(50);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //threadB.start();

        /**
         * 以对象为lock时,即使改变了对象的值,但是方法仍会同步执行
         */
        Thread threadA = new Thread(() -> {
            lockChange.service2();
        },"threadA");

        Thread threadB = new Thread(() -> {
            lockChange.service2();
        },"threadB");

        threadA.start();
        try {
            //即使让threadA改变了userInfoLock的值,还是会同步执行.
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();

    }

    class UserInfo{
        private String username;

        public UserInfo(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

}
