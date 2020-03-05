package ThreadPool;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 21:52
 * @Description:
 * 6个线程池中 有4个的底层实现实际就是
 * ThreadPoolExecutor
 */
public class T13_ThreadPoolExecutor {

    /**
     * Fixed
     * Singled
     * Cached
     * Schedule
     * 这几个线程池 的底层都是ThreadPoolExecutor实现的的
     * public ThreadPoolExecutor(
     * int corePoolSize,   线程池的个数
     * int maximumPoolSize, 线程池最大的个数
     * long keepAliveTime,  线程的存活时间，多上时间没有任务，自动销毁，如果为0表示不被销毁
     * TimeUnit unit, 上一个时间的单位
     * BlockingQueue<Runnable> workQueue)  阻塞队列，高并发容器
     */
}
