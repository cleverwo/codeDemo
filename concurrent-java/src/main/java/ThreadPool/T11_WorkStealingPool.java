package ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 18:17
 * @Description:
 * 第5个线程池
 *  偷线程执行，任务窃取线程池，自己执行完了会去投其他线程没执行的任务去执行
 *  workStealingpool 的线程都是daemon 守护线程，jvm不退出，线程不会中断
 *  workStealingPool 的实现是用ForkJoinPool 实现的。
 */
public class T11_WorkStealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();
        // 打印cpu 是几核的， workStealingPool默认是几核的就初始化几个线程
        System.out.println(Runtime.getRuntime().availableProcessors());
        // 8核的 默认又8个线程，这里初始化9个任务让其执行。
        service.submit(new R(1000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));
        service.submit(new R(2000));

        //main 函数阻塞方法
        System.in.read();
    }

    static class R implements Runnable{
        int time;
        R(int time){
            this.time = time;
        }
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
