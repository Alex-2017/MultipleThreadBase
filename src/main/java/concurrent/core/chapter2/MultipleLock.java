package concurrent.core.chapter2;

/**
 * 2.1.3 多个对象多个锁
 * 两个线程获取的是不同的锁,所以是异步执行的.
 */
public class MultipleLock {

    private int num = 0;

    //synchronized同步方法
    public synchronized void getNum(String username) {
        if ("a".equals(username)) {
            num = 100;
            System.out.println("a set is over");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            num = 200;
            System.out.println("b set is over");
        }
        System.out.println(username + " num = " + num);
    }

    public static void main(String[] args) {
        //创建了多个对象
        InstanceVariable variable1 = new InstanceVariable();
        InstanceVariable variable2 = new InstanceVariable();

        //将两个对象放到不同的线程中
        Thread threadA = new Thread(() -> {
            variable1.getNum("a");
        });
        Thread threadB = new Thread(() -> {
            variable2.getNum("b");
        });

        //两个线程获取的是不同的对象锁,所以会异步执行.
        threadA.start();
        threadB.start();
    }
}
