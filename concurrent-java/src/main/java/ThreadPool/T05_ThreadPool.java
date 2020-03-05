package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 16:24
 * @Description:
 * ThreadPool 线程池
 *
 * 开启一个线程池里面有多个线程
 * 开启了两个对列，一个工作队列，一个完成队列
 * 可以看线程池的底层实现原理： 肯定和高并发容器有关
 */
public class T05_ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        //newFixedThreadPool 固定的线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i=0;i<6;i++){
            service.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        service.shutdown();//正常关闭线程池
        System.out.println(service.isTerminated()); // 看任务是否执行完成
        System.out.println(service.isShutdown()); // 是否关闭了
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
