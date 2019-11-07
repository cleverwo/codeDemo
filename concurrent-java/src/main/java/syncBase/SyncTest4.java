package syncBase;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:29
 * @Description: synchronize 重入的两种情形：
 *  1.同步方法可以调用同步方法   锁中可以在加锁，成两把锁
 *  同一线程可以多次加锁
 *  2.子类的同步方法可以调用父类的同步方法
 */
public class SyncTest4 {
    private int count = 10;

    /**
     * 同步方法1
     */
    synchronized void m1(){
        System.out.println(System.currentTimeMillis());
        count--;
        System.out.println(Thread.currentThread().getName()+ " count"+ count);
    }

    /**
     * 同步方法2
     */
    synchronized void m2(){
        System.out.println(System.currentTimeMillis());
        count--;
        System.out.println(Thread.currentThread().getName()+ " count"+ count);
        //调用m1 同步方法
        m1();
    }

    public static void main(String[] args){
        SyncTest4 t = new SyncTest4();
        /**
         * 线程t1 调用m2，m2中调用m1，m2调用时对t加锁，m1 调用也需要对t加锁，
         * 但m2调用时线程t1已经获取到这把锁，所以m2也能访问
         */
        new Thread(()->t.m2(),"t1").start();
        t.m2();
        /**
         *  新建对象Children 调用同步方法m1，方法m1中调用父类方法m1是可行的，锁重入
         */
        new Children().m1();
    }
}

class Children extends SyncTest4{

    @Override
    synchronized void m1(){
        System.out.println(" children start");
        System.out.println(Thread.currentThread().getName());
        super.m1();
        System.out.println(" children end");
    }
}
