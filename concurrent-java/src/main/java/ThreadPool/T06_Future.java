package ThreadPool;

import java.util.concurrent.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 16:37
 * @Description:
 * Callable 的返回值
 *
 * public class FutureTask<V> extends Object implements RunnableFuture<V>
 * 可取消的异步计算。 该类提供了一个Future的基本实现 ，具有启动和取消计算的方法，查询计算是否完整，并检索计算结果。 结果只能在计算完成后才能检索; 如果计算尚未完成，则get方法将阻止。 一旦计算完成，则无法重新启动或取消计算（除非使用runAndReset()调用计算 ）。
 * A FutureTask可用于包装Callable或Runnable对象。 因为FutureTask实现Runnable ，一个FutureTask可以提交到一个Executor执行。
 * 除了作为独立类之外，此类还提供了protected功能，在创建自定义任务类时可能很有用。
 * 构造方法：
 * FutureTask(Callable<V> callable)
 * 创建一个 FutureTask ，它将在运行时执行给定的 Callable 。
 * FutureTask(Runnable runnable, V result)
 * 创建一个 FutureTask ，将在运行时执行给定的 Runnable ，并安排 get将在成功完成后返回给定的结果。
 * 方法：
 * boolean	cancel(boolean mayInterruptIfRunning)  尝试取消执行此任务。
 * protected void	done() 此任务转换到状态 isDone （无论是正常还是通过取消）调用的受保护方法。
 * V	get() 等待计算完成，然后检索其结果。
 * V	get(long timeout, TimeUnit unit)  如果需要等待最多在给定的时间计算完成，然后检索其结果（如果可用）。
 * boolean	isCancelled() 如果此任务在正常完成之前取消，则返回 true 。
 * boolean	isDone() 返回 true如果任务已完成。
 * void	run() 将此未来设置为其计算结果，除非已被取消。
 * protected boolean	runAndReset() 执行计算而不设置其结果，然后将此将来重置为初始状态，如果计算遇到异常或被取消，则不执行此操作。
 * protected void	set(V v) 将此未来的结果设置为给定值，除非此未来已被设置或已被取消。
 * protected void	setException(Throwable t) 导致这个未来报告一个ExecutionException与给定的可抛弃的原因，除非这个未来已经被设置或被取消。
 *
 *
 * public interface Future<V>
 * A Future计算的结果。 提供方法来检查计算是否完成，等待其完成，并检索计算结果。 结果只能在计算完成后使用方法get进行检索，如有必要，阻塞，直到准备就绪。 取消由cancel方法执行。 提供其他方法来确定任务是否正常完成或被取消。 计算完成后，不能取消计算。 如果您想使用Future ，以便不可撤销，但不提供可用的结果，则可以声明Future<?>表格的类型，并返回null作为基础任务的结果。
 *
 * boolean	cancel(boolean mayInterruptIfRunning) 尝试取消执行此任务。
 * V	get()  等待计算完成，然后检索其结果。
 * V	get(long timeout, TimeUnit unit) 如果需要等待最多在给定的时间计算完成，然后检索其结果（如果可用）。
 * boolean	isCancelled() 如果此任务在正常完成之前被取消，则返回 true 。
 * boolean	isDone() 返回 true如果任务已完成。
 */
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                return 1000;
            }
        });

        new Thread(task).start();
        //等待计算完成，然后检索其结果。 阻塞式的，当task中的任务没有执行完，咋阻塞直到执行完成
        System.out.println(task.get());

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> future = service.submit(new Callable<Integer>(){
            @Override
            public Integer call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(500);
                return 1;
            }
        });

        System.out.println(future.get());
        System.out.println(future.isDone()); //返回 true如果任务已完成。
    }

}
