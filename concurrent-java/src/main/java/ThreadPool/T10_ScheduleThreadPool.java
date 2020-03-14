package ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 18:08
 * @Description:
 * 第四个线程
 * newScheduledThreadPool(int corePoolSize)
 * 创建一个线程池，可以调度命令在给定的延迟之后运行，或定期执行。
 * 定时执行线程池
 */
public class T10_ScheduleThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        Executors.newSingleThreadScheduledExecutor();
        //创建并执行在给定的初始延迟之后，随后以给定的时间段首先启用的周期性动作;
        // 那就是执行将在initialDelay之后开始，然后是initialDelay+period ，
        // 然后是initialDelay + 2 * period ，等等。
        //Runnable command,执行的任务，long initialDelay,延时周期，long period,演示一个的时间，TimeUnit unit演示时间的单位
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,500, TimeUnit.MILLISECONDS);
    }
}
