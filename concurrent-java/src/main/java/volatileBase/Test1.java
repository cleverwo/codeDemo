package volatileBase;

import sun.nio.cs.FastCharsetProvider;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/2/29 11:27
 * @Description: volatile 关键子练习
 */
public class Test1 {
    volatile int num = 0;
    volatile boolean run = true;
    boolean run1 = true;

    public void m() {
        System.out.println("m start");
        while (run){}
        System.out.println("m end");
    }
    //m 的对比方法
    public void m1() {
        System.out.println("m1 start");
        while (run1) { }
        System.out.println("m1 end");
    }

    /**
     * volatile  不保证原子性
     */
    public void m3() {
        for (int i = 0; i < 100; i++) {
            num++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test1 t = new Test1();
        //线程m m1都一直跑， 只有当run和run1为false时，停止，加了volatile关键字的m停止了
        new Thread(() -> t.m(), "m").start();
        new Thread(() -> t.m1(), "m1").start();
        TimeUnit.SECONDS.sleep(1);
        t.run= false;
        t.run1=false;
        // volatile不保证其原子性
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里睡1ms 是让其他线程有执行的可能性
                for (int j = 0; j < 100; j++) {
                    t.num++;
                }
            });
        }
        TimeUnit.SECONDS.sleep(1);
        //System.out.println(t.num);

    }

}
