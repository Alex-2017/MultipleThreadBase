package concurrent.core.chapter1;

/**
 * 1.2.3 实例变量与线程安全
 * 示例2
 * post方法未添加synchronized时,deku线程输出显示userName为BaseC
 * 添加之后,各个线程显示正常.
 */
public class LoginServlet {

    private static String userName;
    private static String password;

    public synchronized static void post(String userName, String password) {
        LoginServlet.userName = userName;
        //如果用户是deku,那么让线程sleep 3s
        if (LoginServlet.userName.equals("deku")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        LoginServlet.password = password;
        System.out.println("LoginServlet.userName is " + LoginServlet.userName + ",LoginServlet.password is " + LoginServlet.password);
    }

    public static void main(String[] args) {
        //创建deku线程,调用post方法
        new Thread(() -> {
            LoginServlet.post("deku", "dekuPass");
        }, "deku").start();

        //创建BaseC线程,调用post方法
        new Thread(() -> {
            LoginServlet.post("BaseC", "BaseCPass");
        }, "BaseC").start();
    }
}
