package concurrent.core.chapter2;

/**
 * 2.1.8 同步不具有继承性
 * 程序输出证明Main中的serviceMethod()仍是同步执行,而Sub的serviceMethod()是异步执行.
 */
public class Main {
    public synchronized void serviceMethod() {
        try {
            System.out.println("thread " + Thread.currentThread().getName() + " beigin run Main " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("thread " + Thread.currentThread().getName() + " end run Main " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        Thread threadA = new Thread(() -> {
            sub.serviceMethod();
        }, "threadA");

        Thread threadB = new Thread(() -> {
            sub.serviceMethod();
        }, "threadB");

        threadA.start();

        try {
            //确保threadA先运行.
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadB.start();
    }
}

class Sub extends Main {
    @Override
    public void serviceMethod() {
        try {
            System.out.println("thread " + Thread.currentThread().getName() + " beigin run Sub " + System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("thread " + Thread.currentThread().getName() + " end run Sub " + System.currentTimeMillis());
            super.serviceMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
