package concurrentBase;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:19
 * @Description:
 * list 的并发容器
 * 写时复制 写的效率很慢，读的速率很快，不会出现脏读的情况
 *
 */
public class T02_CopyOnWriteArrayList {
    public static void main(String[] args) {
        List<String> list =
//                new ArrayList<>(); // 线性表
//                 new Vector<>(); // 同步的线性表
                 new CopyOnWriteArrayList<>(); // 写时复制线性表
        Random r = new Random();
        Thread[] ths = new Thread[100];
        for (int i=0;i<ths.length;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<1000;i++){
                        list.add("a"+r.nextInt(100000));
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndComputeTime(ths);
        System.out.println(list.size());
    }

    static void runAndComputeTime(Thread ths[]){
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
