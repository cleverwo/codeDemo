package concurrentBase;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:35
 * @Description:
 * queue 的并发队列：
 * 生产者消费者 解耦合队列
 * BlockingQueue 阻塞式队列
 *
 * LinkedBlockingQueue 无界队列，不会装满
 */
public class T05_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();

    static Random r = new Random();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i=0;i<100;i++){
                try {
                    strs.put("a"+i); // 如果满了就阻塞等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"p1").start();

        for (int i=0;i<5;i++){
            new Thread(()->{
                for(;;){
                    try {
                        System.out.println(Thread.currentThread().getName()+" take - "+strs.take()); // take空了就阻塞等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }

    }



}
