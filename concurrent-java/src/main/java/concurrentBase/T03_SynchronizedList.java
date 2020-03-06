package concurrentBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:33
 * @Description:
 * Collections.synchronizedXX 方法演示
 */
public class T03_SynchronizedList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> syn_list = Collections.synchronizedList(list);
    }
}
