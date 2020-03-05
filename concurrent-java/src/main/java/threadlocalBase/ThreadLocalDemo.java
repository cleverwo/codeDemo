package threadlocalBase;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangzhendong
 * @Date: 2019/6/11 20:20
 * @Description:
 * ThreadLocal 是线程中的局部变量
 * 是用空间换时间，synchronized是时间换空间
 * 线程局部变量时，当前线程的改变不会造成其他形成的影响
 *
 * ThreadLocal 会造成内存泄漏
 *
 */
public class ThreadLocalDemo {
    volatile static Person person = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    /**
     * 开启两个线程，线程一添加person实例，线程二获取该实例，获取为空，
     * @param args
     */
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一获取t1中线程二添加的实例："+tl.get());
            System.out.println("线程一获取静态变量中的名称："+person.name);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            person.name = "sheng";
            tl.set(new Person());
            System.out.println("线程二添加实例person并设置静态变量person的名称");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }

}

class Person{
    String name = "wang";
}