package concurrent.core.chapter1;

/**
 * 1.9 yield方法
 */
public class YieldThread implements Runnable {

    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < 50000000; i++) {
            //注释yield与未注释yield形成鲜明对比
            Thread.yield();
        }
        System.out.println("花费时间为:" + (System.currentTimeMillis() - beginTime));
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new YieldThread());
        thread.start();
    }
}
