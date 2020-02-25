package Queue;

import java.util.PriorityQueue;

/**
 * @Auther: 10413
 * @Date: 2020/2/25 11:45
 * @Description:
 * PriorityQueue 优先队列 常用方法：
 * 一个基于优先级的无界优先级队列，优先级队列的元素按照其自然顺序进行排序，
 * 或者根据构造队列时提供的 Comparator 进行排序，具体取决于所使用的构造方法
 */
public class PriorityQueueTest {
    /**
     * 介绍：
     * PriorityQueue使用跟普通队列一样，唯一区别是PriorityQueue会根据排序规则决定谁在队头，谁在队尾。
     */

    public static void main(String[] args) {
        //基本用法
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(4);
        queue.offer(3);
        queue.offer(2);
        queue.offer(1);
        // 默认从小到大排序
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }


}
