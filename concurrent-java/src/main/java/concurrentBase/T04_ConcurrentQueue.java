package concurrentBase;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:33
 * @Description:
 * queue 相关的高并发容器
 * queue 队列 的接口实现里常用的时linkedlist
 * 高并发容器常用就是ConcurrentLinkedQueue
 *
 * queue 时单端队列， deque 是双端队列
 * 这里主要演示了queue的常用的方法
 *
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> queue = new ConcurrentLinkedQueue<>();

        for (int i=0;i<10;i++){
            queue.offer("a"+i);
        }
        System.out.println(queue);
        System.out.println(queue.size());
        System.out.println(queue.poll());
        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.size());
    }



}
