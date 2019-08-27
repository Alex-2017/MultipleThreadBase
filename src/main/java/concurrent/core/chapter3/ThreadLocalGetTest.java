package concurrent.core.chapter3;

/**
 * 3.3.3 解决ThreadLocal变量首次get()返回null问题
 */
public class ThreadLocalGetTest {

    //继承ThreadLocal,重写initialValue()方法
    static class ThreadLocalExt extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return "自定义初始值";
        }
    }

    public static void main(String[] args) {
        ThreadLocalExt ext = new ThreadLocalExt();
        System.out.println(ext.get());
    }

}
