package concurrent.core.chapter2;

/**
 * 2.1.2 实例变量非线程安全
 */
public class InstanceVariable {

    private int num = 0;

    //有线程安全问题,添加synchronized关键字.
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
        InstanceVariable variable = new InstanceVariable();

        Thread threadA = new Thread(() -> {
            variable.getNum("a");
        });

        Thread threadB = new Thread(() -> {
            variable.getNum("b");
        });

        threadA.start();

        threadB.start();
    }
}
