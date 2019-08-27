package concurrent.core.chapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * 2.2.7 将任意对象作为对象监视器
 */
public class MyOneList {

    private List<String> list = new ArrayList<>();

    public synchronized void add(String data) {
        list.add(data);
    }

    public synchronized int getSize() {
        return list.size();
    }
}
