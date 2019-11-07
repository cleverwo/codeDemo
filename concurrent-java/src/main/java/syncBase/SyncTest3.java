package syncBase;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:24
 * @Description: synchronized 对读写机制的操作
 * 不对读加锁导致的脏读问题
 */
public class SyncTest3 {
    private String name;
    private double balance;

    /**
     * 写逻辑
     * @param name
     * @param balance
     */
    public synchronized void set(String name,Double balance){
        this.name = name;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    /**
     * 读逻辑
     * @param name
     * @return
     */
    public synchronized Double getBalance(String name){

        return this.balance;
    }

    public static void main(String[] args){
        System.out.println(System.currentTimeMillis()/1000);
        SyncTest3 account = new SyncTest3();
        new Thread(()->account.set("wang",100.00)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " balance"+account.getBalance("wang") );
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " balance"+account.getBalance("wang") );
        System.out.println(System.currentTimeMillis()/1000);
    }

}
