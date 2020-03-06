package concurrentBase;

import javafx.concurrent.Task;

import javax.naming.Name;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 14:35
 * @Description:
 *
 * delayQueue 执行定时任务
 */
public class T07_DelayQueue {
    //DelayQueue 的任务必须实现delayed 的接口 Delayed接口又实现的Comparable 所以要实现compareTo方法
    static BlockingQueue<MyTask> strs = new DelayQueue<>();

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask((now + 1000),"t1"); //1s 后执行
        MyTask t2 = new MyTask((now + 2000),"t2");
        MyTask t3 = new MyTask((now + 1500),"t3");
        MyTask t4 = new MyTask((now + 2500),"t4");
        MyTask t5 = new MyTask((now + 500),"t5");

        strs.put(t1);
        strs.put(t2);
        strs.put(t3);
        strs.put(t4);
        strs.put(t5);
        //根据等待时间的长度，对这些任务进行了排序，等的时间短的先执行

        System.out.println(strs);

        for (int i=0;i<5;i++){
            System.out.println(strs.take());
        }
    }

    static class MyTask implements Delayed {
        long runningTime;
        String name;
        MyTask(long runningTime,String name){
            this.runningTime = runningTime;
            this.name = name;
        }
        @Override
        public long getDelay(TimeUnit unit) {
            //long convert(long sourceDuration, TimeUnit sourceUnit) 将给定单位的给定持续时间转换为本机。
            return unit.convert(runningTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS)<o.getDelay(TimeUnit.MILLISECONDS)){
                return -1;
            }else if (this.getDelay(TimeUnit.MILLISECONDS)>o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            }else{
                return 0;
            }
        }

        @Override
        public String toString() {
            return name + " : "+ runningTime;
        }
    }

}
