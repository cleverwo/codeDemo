package concurrentBase;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:35
 * @Description:
 *
 * 有界阻塞式队列
 */
public class T06_ArrayBlockingQueue {

    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<10;i++){
            strs.put("a"+i);
        }

        // strs 满了
        strs.put("a"); // 加不进去会阻塞
        strs.add("e"); // add 报异常
        strs.offer("ss"); // offer 不报异常，offer有返回值
        strs.offer("ss",1, TimeUnit.SECONDS); // offer 按时间段阻塞

        System.out.println(strs);
    }
}
