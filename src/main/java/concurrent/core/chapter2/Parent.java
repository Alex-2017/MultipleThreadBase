package concurrent.core.chapter2;

/**
 * 2.1.6 synchronized锁重入
 * 当存在父子类继承关系时,子类是完全可以通过可重入锁调用父类的同步方法的.
 */
public class Parent {

    protected int i = 10;

    public synchronized void operateNumInParent() {
        try {
            i--;
            System.out.println("parent print i= " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Child child = new Child();
            child.operateNumInChild();
        });

        thread.start();
    }

}

class Child extends Parent {
    public synchronized void operateNumInChild() {
        while (i > 0) {
            i--;
            System.out.println("child print i= " + i);
            //在已经获得锁的情况下,还可继续调用父类同步方法
            super.operateNumInParent();
        }
    }
}