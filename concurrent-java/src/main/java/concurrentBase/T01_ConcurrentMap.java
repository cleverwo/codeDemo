package concurrentBase;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 13:50
 * @Description: map 的并发容器concurrentMap
 * 有两个实现类 concurrentHashMap 和 concurrentSkipListMap，并发哈希表和跳表
 * map/set的并发容器有： map和set本质是一样的东西，map有value，set只有key
 * HashTable
 * Collections.synchronizedMap 返回加了锁的map 底层就是对map的所有操作加了synchronized关键字
 *
 * concurrentHashMap
 * concurrentSkipListMap
 */
public class T01_ConcurrentMap {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>(); // 并发 并且 排序，插入的效率比较低对应红黑树

//        Map<String,String> map = new Hashtable<>(); // concurrentHashMap 的效率高于hashtable
//        Map<String,String> map = new HashMap<>();// Collections.synchronizedXXX
//        Map<String,String> map_syn = Collections.synchronizedMap(map);
        //TreeMap 底层实现红黑树， 排序了的avl树，弱avl树
        Random r = new Random();
        Thread[] ths  = new Thread[100];
        CountDownLatch latch = new CountDownLatch(100);
        long start = System.currentTimeMillis();
        for (int i =0;i<ths.length;i++){
            ths[i] = new Thread(()->{
                //往map里装了10000个随机字符串，执行完后门闩减一
                for (int j=0;j<10000;j++){
                    map.put("a"+r.nextInt(10000000),"a"+r.nextInt(10000000));
                }
                latch.countDown();
            });
        }
        //所有线程都启动
        Arrays.asList(ths).forEach(t->t.start());
        // latch.await 主线程等待，直到所有线程执行完后执行
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
