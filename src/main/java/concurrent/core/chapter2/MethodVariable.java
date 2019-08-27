package concurrent.core.chapter2;

/**
 * 2.1.1 方法内的变量为线程安全
 */
public class MethodVariable {

    public void getNum(String username) {
        int num = 0;
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
        MethodVariable variable = new MethodVariable();

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
