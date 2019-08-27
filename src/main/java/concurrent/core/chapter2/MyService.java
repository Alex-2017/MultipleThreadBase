package concurrent.core.chapter2;

/**
 * 2.2.7 将任意对象作为对象监视器
 * MyOneList的方法是同步的,但是它是被异步调用的,所以会出现脏读的情况.
 * 解决方法:使用synchronized(任意对象)
 */
public class MyService {

    public void addMyOneList(MyOneList myOneList, String data) {
        //使用synchronized(任意对象),确保myOneList在此方法中的操作是同步的.
        synchronized (myOneList) {
            if (myOneList.getSize() < 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myOneList.add(data);
            }
        }
    }

    public static void main(String[] args) {
        MyOneList myOneList = new MyOneList();

        Thread threadA = new Thread(() -> {
            MyService service = new MyService();
            service.addMyOneList(myOneList, "threadA");
        }, "threadA");

        Thread threadB = new Thread(() -> {
            MyService service = new MyService();
            service.addMyOneList(myOneList, "threadB");
        }, "threadB");

        threadA.start();
        threadB.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myOneList.getSize());
    }
}
