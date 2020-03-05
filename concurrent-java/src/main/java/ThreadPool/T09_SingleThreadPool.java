package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 17:44
 * @Description:
 * 第三个线程池：
 * 线程池里只有一个线程
 */
public class T09_SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            service.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + j);
            });
        }
        System.out.println("线程池状态：" + service);
    }
}
