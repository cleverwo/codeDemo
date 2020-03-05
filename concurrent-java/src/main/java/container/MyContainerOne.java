package container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/16 14:01
 * @Description: volatile关键字的使用例子，
 *  一个容器，又add 和size 方法
 *  启动两个线程，线程1添加10个元素，线程2实时监控元素个数，个数到5时，线程2给出提示并结束
 *
 *  count 添加volatile后对线程2可见，但线程2的死循环检测效率太低，但线程1 count加到5是通知线程2执行怎么做
 *
 *  wait和notify wait会释放锁，notify不释放锁
 *
 *  使用latch（门闩）代替 wait notify进行通知
 *  latch 的优点是不用加锁，只涉及通信 用synchronize + wait 处理太繁琐，
 *  可以用countdownlatch /cyclicbarrier/semaphore 处理
 */
public class MyContainerOne {

    //volatile 共享关键字
    volatile List<Integer> count = new ArrayList<>();

    public void add(Integer num){
        count.add(num);
    }

    public Integer size(){
        return count.size();
    }

    public static void main(String[] args){
        solution1();
       //solution2();
       //solution3();

    }

    /**
     * volatile 关键字控制多线程访问相同关键字
     */
    public static void solution1(){
        MyContainerOne t = new MyContainerOne();

        /* volatile 共享字段 */
        new Thread(()->{
            for(int i=0;i<10;i++){
                t.add(i);
                System.out.println(Thread.currentThread().getName()+ " "+ i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        new Thread(()->{
            while (true){
                if (t.size() == 5){
                    System.out.println("t2 结束 "+t.size());
                    break;
                }
            }
        }).start();
    }

    /**
     * wait/notify 解决线程2 监控线程1 的容器数量
     * wait 释放锁，notify/notifyAll不释放锁
     */
    public static void solution2() {
        MyContainerOne t = new MyContainerOne();
        /*
        synchronized + wait/notify
        */
        final Object o = new Object();
        new Thread(()->{
            synchronized (o){
                System.out.println("a1 启动");
                if(t.size() != 5){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("a1 停止");
                o.notifyAll();
            }
        },"a1").start();
        new Thread(()->{
            synchronized (o){
                System.out.println("a2 启动");
                for(int i=0; i<10; i++){

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (t.size() == 5){
                        o.notify();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (t.size() == 8){
                        o.notifyAll();
                        try {
                            o.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("a2 停止");
            }
        },"a2").start();
        new Thread(()->{
            synchronized (o){
                System.out.println("a3 start ");
                if (t.size() != 8){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("a3 end ");
                o.notifyAll();
            }
        },"a3").start();


    }

    /**
     * CountDownLatch 门闩控制
     * countdownlatch/cyclicbarrier/semaphore
     */
    public static void solution3(){
        MyContainerOne t = new MyContainerOne();
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(()->{
            System.out.println("b1 开始");
            if (t.size()!= 5){
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("b1 执行完毕");
        },"b1").start();

        new Thread(()->{
            System.out.println("b2 start");
            for (int i=0;i<10;i++){
                t.add(i);
                System.out.println(Thread.currentThread().getName() + " "+ t.size());
                if (t.size() == 5){
                    latch.countDown();
                }
            }
            System.out.println("b2 end");
        },"b2").start();
    }
}
