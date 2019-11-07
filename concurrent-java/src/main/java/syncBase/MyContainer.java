package syncBase;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangzhendong
 * @Date: 2019/6/10 18:23
 * @Description: 消费者生产者容器 利用synchronized+ wait + notify创建容器
 *
 * 定义了容器lists 最大容量是10 当前容量为count
 * put 为生产  get为消费
 *
 */
public class MyContainer<T> {

    final private LinkedList<T> lists = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0; //当前容量

    // wait 和 while 一起使用
    public synchronized void put(T t){
        System.out.println(Thread.currentThread().getName()+ " start");
        while (lists.size() == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        count++;
        System.out.println(Thread.currentThread().getName()+ ": "+ t + " 当前容量： "+ count);
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+ " end");
    }

    public synchronized T get(){
        System.out.println(Thread.currentThread().getName()+ " start");
        T t = null;
        while (lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        count--;
        System.out.println(Thread.currentThread().getName()+ ": "+ t+ " 当前容量："+count);
         return t;
    }

    public static void main(String[] args){
        MyContainer<String> c = new MyContainer<>();

        //消费者
        for (int i=0;i<3;i++){
            new Thread(()->{
                for (int j=0; j<5;j++){
                    System.out.println(Thread.currentThread().getName()+ " "+ c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //生产者
        for (int i=0;i<2;i++){
            new Thread(()->{
                for (int j=0;j<25;j++){
                    c.put( "生产糖果"+ j);
                }
            },"p"+i).start();
        }

    }

}
