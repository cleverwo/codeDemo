package syncBase;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 16:20
 * @Description: 同步方法和非同步方法可否一起调用
 * 如：m方法调用是，调用m2方法
 * 可以一起调用，非同步方法不需要获取锁
 */
public class SyncTest2 {

    /**
     * 同步方法m
     */
    public synchronized void m(){
        System.out.println(Thread.currentThread().getName()+ " m1 start...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " m1 end");
    }

    /**
     * 非同步方法m2
     */
    public void m2(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " m2 start");
    }

    public synchronized void m3(){
        System.out.println(Thread.currentThread().getName() + " m3 start..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m3 end");
    }

    public static void main(String[] agrgs){
        SyncTest2 t = new SyncTest2();
        /**
         * 新建一个对象t， 开启三个线程t1，t2，t3
         * t1开始启动时调用t对象的m方法，这时对t对象加锁，其他线程不能在调用t对象的同步方法 如：线程t3无法调用m3方法
         * 而线程t2可以调用m2方法，因为m2为普通方法，可以在同步方法调用时调用，因为m2方法不需要对 t对象加锁
         * 线程t3调用m3方法，需要对t对象加锁，因为线程t1已经加锁了，t3获取不到锁，只能等待t1执行完后去获取锁，加锁调用
         * m3方法。
         */
        new Thread(()->t.m(), "t1").start();
        new Thread(()->t.m3(),"t3").start();
        new Thread(()->t.m2(),"t2").start();
        /**
         * java8新特性lambda表达式
         * 等同于
         */
        /*
        new Thread(t::m,"t1").start();
        */
       /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        });
        */
    }
}
