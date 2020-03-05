package ThreadPool;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 15:59
 * @Description:
 *  ExecutorService 是executor的服务，
 *  ExecutorService 提供了submit方法中，可以提交Callable任务也可以是runnable方法。
 *
 *  public interface ExecutorService extends Executor
 * 一个Executor ，提供方法来管理终端和方法，可以产生Future为跟踪一个或多个异步任务执行。
 * 一个ExecutorService可以关闭，这将导致它拒绝新的任务。 提供了两种不同的方法来关闭ExecutorService 。 shutdown()方法将允许先前提交的任务在终止之前执行，而shutdownNow()方法可以防止等待任务启动并尝试停止当前正在执行的任务。 一旦终止，执行者没有任务正在执行，没有任务正在等待执行，并且不能提交新的任务。 应关闭未使用的ExecutorService以允许资源的回收。
 * 方法submit延伸的基方法Executor.execute(Runnable)通过创建并返回一个Future可用于取消执行和/或等待完成。 方法invokeAny和invokeAll执行invokeAll执行最常用的形式，执行任务集合，然后等待至少一个或全部完成。 （类别ExecutorCompletionService可用于编写这些方法的自定义变体。）
 * Executors类为此包中提供的执行程序服务提供了工厂方法。
 */
public class T02_ExecutorService {
}
