package syncBase;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/12 14:19
 * @Description:
 * synchronized 关键字基本应用
 * 1，静态方法枷锁
 * 2.方法区枷锁
 * synchronized  对对象加锁 同步方法
 */
public class SyncDemo {
    private int count = 10;
    private static int count2 = 10;
    private Object o = new Object();

    /**
     * synchronized 基本用法: 对o对象加锁，o为对象锁，获取到o对象锁才能执行下列代码。
     */
    public void t1(){
        synchronized (o){
            count--;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " count"+ count);
        }
    }

    /**
     * synchronized ： 对o对象加锁的过程中创建了一个新的Object 对象，浪费了内存空间，直接对该类本身的对象加锁
     * 就等同于对o加锁。
     */
    public void t2(){
        synchronized (this){
            count--;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+ " count "+ count);
        }
    }

    /**
     * 写法不一样，效果与t2方法相同
     */
    public synchronized void t3(){ // 等同与synchronized(this)
        count--;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+ " count "+ count);
    }

    /**
     * 访问静态资源加锁时，因为静态方法不需要实例化对象，直接类名就能够调用，所以在对对象本身加锁已经不行了，
     * 因为本身并没有实例化，所以对类名的Class这个对象加锁，在访问静态资源。
     */
    public static synchronized void m1(){
        synchronized (SyncDemo.class){
            count2--;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " count2 "+ count2);
        }
    }
    public static synchronized void m2(){ //相当于锁定 T。class
        count2--;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " count2 "+ count2);
    }


    public static void main(String[] args){
        SyncDemo t = new SyncDemo();
        /**
         * 线程t1，对o加锁，所以在o锁未释放时，t1方法是无法访问的
         * 线程t2，t3 都是对t对象加锁，所以 t2，t3的结果一定是递减的，t2，t3方法实际上是一个方法
         * 线程t4，对SyncDemo.Class对象加锁，静态资源count2的访问。
         */
        new Thread(t::t1,"t1").start();
        new Thread(t::t2,"t2").start();
        new Thread(t::t3,"t3").start();
        new Thread(SyncDemo::m1,"t4").start();
    }

}
