package concurrent.core.chapter1;

import java.util.Random;

/**
 * 1.10.2 优先级具有规则性
 * 按照代码①执行,threadB先执行完,说明线程的优先级具有一定的规则性,也就是CPU尽量将执行资源让给优先级比较高的线程.
 * 1.10.3 优先级具有随机性
 * 按照代码②执行,thread并没有完全根据priority执行,说明线程的优先级具有随机性,也就是优先级较高的线程不一定每次都先执行完.
 */
public class PriorityThread2 {

    public static void main(String[] args) {
        for (int j = 0; j < 6; j++) {
            Thread threadA = new Thread(() -> {
                for (int i = 0; i < 5000000; i++) {
                    Random random = new Random();
                    random.nextInt();
                }
                System.out.println("###### threadA is done");
            }, "threadA");

            Thread threadB = new Thread(() -> {
                for (int i = 0; i < 5000000; i++) {
                    Random random = new Random();
                    random.nextInt();
                }
                System.out.println("$$$$$$ threadB is done");
            }, "threadB");

            //代码①
            //threadA.setPriority(1);
            //threadB.setPriority(10);
            //代码②
            threadA.setPriority(5);
            threadB.setPriority(6);

            threadA.start();
            threadB.start();
        }
    }

}
