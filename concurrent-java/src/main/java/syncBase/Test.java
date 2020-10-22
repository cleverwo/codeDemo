package syncBase;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/3/7 15:05
 * @Description: 锁消除 和 锁粗化
 */
public class Test {
    public void test() throws InterruptedException {
        Object obj = new Object();
        synchronized (Thread.currentThread()) {
            obj.wait();
            obj.notify();
        }
    }

    public static void main(String[] args) {
        String key = null;
        Integer value = null;
        Hashtable table = new Hashtable();
        key = "3";
        value = 4;
        table.put(key, value);
        key = "2";
        value = 2;
        table.put(key, value);
        key = "3";
        value = 2;
        table.put(key, value);
        iteratorEntryset(table);
    }

    private static void iteratorEntryset(Hashtable table) {


        if (table == null)
            return;
        String key = null;

        Integer integer =null;

        Iterator iter =table.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            key = (String) entry.getKey();
            integer = (Integer) entry.getValue();
            System.out.println(key + " - " + integer.intValue());
        }


    }
}
