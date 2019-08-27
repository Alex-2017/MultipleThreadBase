package concurrent.core.chapter3;

/**
 * 3.4.1 值继承
 * 3.4.2 值继承再修改
 */
public class InheritableThreadLocalTest {

    public static void main(String[] args) {
        //使用ThreadLocal对象,子线程无法获得在父线程中设置的值.
        //ThreadLocal<String> threadLocal = new ThreadLocal<>();

        //使用InheritableThreadLocal则可解决这一问题
        //InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

        //使用自定义InheritableThreadLocal对象,可更改从父类继承到的值
        InheritableThreadLocalExt threadLocal = new InheritableThreadLocalExt();

        threadLocal.set("hello world");

        //childThread在main线程中创建,那么childThread就是main线程的子线程.
        Thread childThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " get threadLocal value is " + threadLocal.get());
        }, "childThread");

        childThread.start();

        System.out.println(Thread.currentThread().getName() + " get threadLocal value is " + threadLocal.get());
    }

    static class InheritableThreadLocalExt extends InheritableThreadLocal {
        //重写childValue()方法,可对父类的返回值进行自定义修改
        @Override
        protected Object childValue(Object parentValue) {
            return super.childValue(parentValue) + " from subThread";
        }
    }


}
