package syncBase;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:36
 * @Description:
 * 遇到异常不捕获 锁就会被释放
 * 加锁时不要对字符串常量进行加锁
 */
public class SyncTest5 {
    int count = 0;

    synchronized void m(){
        System.out.println(Thread.currentThread().getName() + "start");
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+ " count "+ count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5){
                int i = 1/0;
            }
        }
    }

    public static void main(String[] args){
        SyncTest5 t = new SyncTest5();
        new Thread(()->t.m(),"t1").start();
        /**
         * 线程t1 抛出异常 释放锁，t2线程才可以执行
         */
        new Thread(()->t.m(),"t2").start();

    }
}
