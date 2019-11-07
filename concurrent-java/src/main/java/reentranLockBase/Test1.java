package reentranLockBase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/16 15:39
 * @Description:
 *
 * reentranlock 的lock()锁定是无法打断的
 *
 */
public class Test1 {
    static Lock lock = new ReentrantLock();


    void d(){
        try {
            lock.lockInterruptibly();
            System.out.println("d start");
            TimeUnit.SECONDS.sleep(5);
            System.out.println("d end");
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

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                System.out.println("t1 start ");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1");

        Thread t2 = new Thread(()->{
           lock.lock();
            try {
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            } catch (InterruptedException e) {
                System.out.println("t2 interrupted");
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread t3 = new Thread(()->{
            try {
                lock.lockInterruptibly();
                System.out.println("t3 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t3 end");
            } catch (InterruptedException e) {
                System.out.println("t3 interrupted");
                e.printStackTrace();
            }finally {
                boolean locked = lock.tryLock();
                if (locked){
                    lock.unlock();
                }
            }
        });

        t1.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
        t2.interrupt();//阻断t2线程
        t3.start();
        t3.interrupt();
        //执行时 t2 时无法执行的，因为lock()是无法被打断的， t3是用lockInterruptibly就可以被打断了
    }

}
