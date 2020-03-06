package concurrentBase;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:36
 * @Description:
 *
 * 提供了一个transfer方法
 * 消费者先启动，生产者启动后不会去队列里找任务，而是先找有无消费者，直接把任务扔给消费者
 */
public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        //实时消息处理，生产的东西必须马上消费掉
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        //消费者线程先启动
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //生产者，没有消费者时这里会阻塞
        strs.transfer("aaa");
        //put 和offer方法不会造成这中情况

        /*new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }
}
