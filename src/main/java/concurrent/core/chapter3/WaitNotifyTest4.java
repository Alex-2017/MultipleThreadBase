package concurrent.core.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.10 等待wait的条件发生变化
 */
public class WaitNotifyTest4 {

    private List list = new ArrayList();

    private Object lock = new Object();

    public void add() {
        synchronized (lock) {
            list.add(new Object());
            lock.notifyAll();
        }
    }

    public void subtract() {
        synchronized (lock) {
            //if语句只会判断一次
            if (this.list.size() == 0) {
            //while语句会在每次wait恢复之后继续进行判断,如果满足条件继续wait.
            //while (this.list.size() == 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " begin wait");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + " end wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.list.remove(0);
            System.out.println(Thread.currentThread().getName() + " 's list size is " + list.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WaitNotifyTest4 test = new WaitNotifyTest4();

        Thread subA = new Thread(test::subtract, "subA");
        Thread subB = new Thread(test::subtract, "subB");
        Thread addThread = new Thread(test::add, "addThread");

        subA.start();
        subB.start();

        //确保subA和subB都执行了wait方法
        Thread.sleep(1000);

        addThread.start();
    }


}
