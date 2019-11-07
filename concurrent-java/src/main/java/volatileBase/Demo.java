package volatileBase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:39
 * @Description:
 * volatile 关键字 不稳定的，修饰的变量 可以重复读取到cpu中，保证不同线程之间共用变量交互
 * 只保证可见性，不能保证多个线程同时修改时的原子性
 */
public class Demo {
    private /*volatile*/ boolean running = true;
    private volatile int count = 0;

    /**
     * 死循环，当running变false时，停止执行，既看执行此方法的线程释放动态获取running的值
     * 加入volatile后不同线程都可以实时的获取running的数值，从而实现不同线程之间的资源共享
     */
    public void m(){
        System.out.println("m start");
        while (running){
            //System.out.println(Thread.currentThread().getName()+ " count "+ count++);
        }
        System.out.println("m end");
    }

    /**
     * m 的改进方法，目的区别加volatile关键字与不见关键字，让死循环中睡眠1毫秒的情况，
     * 发现睡眠1毫秒后线程也可以重新获取running值。因为，该线程睡眠了，其他线程可能会重新载入
     * running的值。但这样是不安全的
     */
    public void m1(){
        System.out.println("m1 start");
        while (running){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(Thread.currentThread().getName()+ " count "+ count++);
        }
        System.out.println("m1 end");
    }

    /**
     * volatile  不保证原子性
     */
    public void m3(){
        for (int i = 0;i<100;i++){
            count++;
        }
    }

    /** Atomic*** 为java提供的最底层的原子操作类型，count++是不具有原子性的，但atomic** 是具有原子性的 */
    AtomicInteger count1 = new AtomicInteger(0);

    /** Atomic** 比 synchronized 效率更高 */
    void f(){
        count1.incrementAndGet(); // 同conut++，具有原子性，不会被打断
    }


    public static void main(String[] args){
        /**
         * 开启两个线程 main 和 t1
         * t1执行 t的m方法， 首先获取t对象锁，这是在执行m，m的执行获取了堆内存中的 running变量
         * main 主线程修改了 running 变量，但线程t1 在cpu中执行时已经获取了running对象，没有在重新获取running对象，
         * 所以 t1 中 的m方法一直执行下去，增加volatile关键字表示 running 变量为不稳定的，即改变后，t1线程所在的cpu
         * 会重新读取java内存中的running 变量（java内存模型的实现看JVM和JMM），这是读取到running 为false，t1中的m结束。
         * java memory modal
         * 同理，当m方法增加 一个睡眠等待功能，如m1方法，则t1线程睡眠后会重新读取running字段，所以m1方法可以结束
         * 不可靠
         */
        Demo t = new Demo();
/*
        new Thread(()->t.m(),"t1").start();
        new Thread(()->t.m1()).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
*/
        List<Thread> threads = new ArrayList<>();
        for(int i=0;i<10;i++){
            threads.add(new Thread(t::m3,"thread"+ i+1));
            threads.add(new Thread(t::f,"thread"+ i+1));
        }
        threads.forEach((o)->o.start());
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count1);
        System.out.println(t.count);

    }
}
