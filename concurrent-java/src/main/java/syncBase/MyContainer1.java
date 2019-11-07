package syncBase;


import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: wangzhendong
 * @Date: 2019/6/11 19:35
 * @Description: 消费者生产者容器 利用Lock + Condition创建容器
 * 该方式可以精确指定那些线程被唤醒，比synchronize+wait+notify 更有效率
 *
 * 定义了容器lists 最大容量是10 当前容量为count
 * put 为生产  get为消费
 */
public class MyContainer1<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0; //当前容量

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition customer = lock.newCondition();


    public void put(T t) {
        lock.lock();
        try {
            while (lists.size() == MAX) {
                producer.await();
            }
            lists.add(t);
            count++;
            customer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        lock.lock();
        T t = null;
        try {
            while (lists.size() == 0) {
                customer.await();
            }
            t = lists.removeFirst();
            count--;
            producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }


    public static void main(String[] args) {
        MyContainer1<String> c = new MyContainer1<>();

        //消费者
        for(int i=0;i<2;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ " start");
                System.out.println(Thread.currentThread().getName()+ " 消费 "+ c.get());
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //生产者
        for (int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ " 开始生产");
                c.put("糖果"+Thread.currentThread().getName());
            },"p"+i).start();
        }
    }

}
