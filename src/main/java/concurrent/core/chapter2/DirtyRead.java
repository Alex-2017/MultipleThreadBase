package concurrent.core.chapter2;

/**
 * 2.1.5 脏读
 * 出现脏读是因为public void getValue()不是同步的,所以可以在任何时刻调用.添加synchronized即可解决问题.
 */
public class DirtyRead {

    private String name = "A";
    private String password = "AA";

    public synchronized void setValue(String name, String password) {
        try {
            this.name = name;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setValue method thread name = " + Thread.currentThread().getName() + ",name is " + name + ", password is " + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getValue() {
        System.out.println("getValue method thread name = " + Thread.currentThread().getName() + ",name is " + name + ", password is " + password);
    }

    public static void main(String[] args) throws InterruptedException {
        DirtyRead dirtyRead = new DirtyRead();
        Thread threadA = new Thread(() -> {
            dirtyRead.setValue("B", "BB");
        });
        threadA.start();
        //确保threadA先执行
        Thread.sleep(1000);
        dirtyRead.getValue();
    }


}
