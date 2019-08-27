package concurrent.core.chapter2;

/**
 * 2.1.7 出现异常,锁自动释放.
 */
public class ExceptionLock {
    public synchronized void service() {
        String threadName = Thread.currentThread().getName();
        if ("a".equals(threadName)) {
            System.out.println("threadName is " + threadName + " run beginTime = " + System.currentTimeMillis());
            while (true) {
                if ("0.123456".equals(String.valueOf(Math.random()).substring(0, 8))) {
                    System.out.println("threadName is " + threadName + " exception time = " + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("threadName is " + threadName + " run beginTime = " + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExceptionLock exceptionLock = new ExceptionLock();
        Thread threadA = new Thread(() -> {
            exceptionLock.service();
        }, "a");

        Thread threadB = new Thread(() -> {
            exceptionLock.service();
        }, "b");

        threadA.start();
        //确保threadA获取锁,先执行.
        Thread.sleep(500);
        threadB.start();
    }
}
