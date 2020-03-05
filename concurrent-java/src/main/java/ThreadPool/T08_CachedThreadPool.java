package ThreadPool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 17:19
 * @Description:
 * 第二个线程池：
 * 缓存弹性的线程池，cachedThreadPool
 * 来一个任务就起一个线程，直到达到系统负载，不能超过Integer.MAXVALUE
 * 每一个线程有一个生命周期，当这个线程默认60s没有任务，就自动销毁
 * 这个aliveTime可以自己指定
 */
public class T08_CachedThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println("线程池状态："+ service);

        for (int i=0;i<2;i++){
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println("线程池状态："+ service);
        TimeUnit.SECONDS.sleep(80);
        System.out.println("线程池状态："+ service);
    }
}
