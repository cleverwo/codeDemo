package reentranLockBase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/16 15:24
 * @Description:
 *  ReentrantLock 是手动锁，所以必须手动释放锁
 *  synchronized 加锁 碰到异常jvm会自动释放，reentrantLock 则需要手动释放
 *
 *  ReentrantLock 锁定常用的方法有四个：
 *  lock(),
 *  trylock(),
 *  trylock(time)
 *  lockInterruptibly()
 *
 *  ReentrantLock 可以规定为公平锁， 所谓公平锁即 谁等待长谁先执行，
 *  synchronized 为不公平锁，默认reentrantLock为不公平锁，参数为true为公平锁
 */
public class Demo {

    Lock lock = new ReentrantLock();
    Lock lock2 = new ReentrantLock(true); //公平锁

    /**
     * reentranlock 手动锁锁定demo
     * 遇到异常不会自动释放锁，需要在finally里手动释放锁
     */
    void a() {
        try {
            lock.lock(); //synchronized(this)
            System.out.println("t1 start");
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("t1 end");
        }
    }

    /**
     * 简单锁定应用
     */
    void b(){
        lock.lock();
        System.out.println("t2 start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
        System.out.println("t2 end");
    }

    /**
     * trylock 是尝试锁定，代码中 lock尝试锁定5秒，锁定不了他还是继续进行了。
     * 锁定了，在最后解锁.  感觉很傻逼的一个方法
     */
    void c(){
        boolean locked = false;
        try {
            locked = lock.tryLock(5,TimeUnit.SECONDS);
            System.out.println("t3 start");
            System.out.println(Thread.currentThread().getName() + " 加锁："+ locked );
            System.out.println("t3 end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (locked){
                lock.unlock();
            }
        }
    }

    /**
     * lockInterruptibly 是可以被打断的锁，即长时间获取不到锁是，可以被其他线程打断，并抛出异常
     */
    void d(){
        try {
            lock.lockInterruptibly();
            System.out.println("t4 start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("t4 end");
        } catch (InterruptedException e) {
            System.out.println("interrupted");
            //e.printStackTrace();
        }finally {
            boolean locked = lock.tryLock();
            System.out.println(Thread.currentThread().getName() +" "+ locked);
            if (locked){
                lock.unlock();
            }
        }
    }

    void aa(){
        for (int i = 0; i<10;i++){
            lock2.lock();
            System.out.println(Thread.currentThread().getName() + " running");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2.unlock();
        }
    }


    public static void main(String[] args){
        Demo t = new Demo();
        // t1 锁定
        new Thread(t::a,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // t2 无法运行
        new Thread(t::b,"t2").start();
        //t3 无法运行
        new Thread(t::c,"t3").start();
        Thread t4 = new Thread(t::d,"t4");
        t4.start();
        //t4.interrupt();

        //公平锁demo
       // new Thread(t::aa,"tt1").start();
       // new Thread(t::aa,"tt2").start();
    }





}
