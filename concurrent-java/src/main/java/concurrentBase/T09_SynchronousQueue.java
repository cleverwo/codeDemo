package concurrentBase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:36
 * @Description:
 * 特殊的transferQueue
 */
public class T09_SynchronousQueue { //容量为0的队列

    public static void main(String[] args) throws InterruptedException {
        //实时消息处理，生产的东西必须马上消费掉
        BlockingQueue<String> strs = new SynchronousQueue<>();

        //消费者线程先启动
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //生产者
        strs.put("aaa"); // 没有消费时阻塞，syncqueue是没有容量的add不进去
        //strs.add("aaa"); // 这里add不进去，报错，synchronousQueue是容量为0的

        System.out.println(strs.size());
    }
}
