package ThreadPool;

import com.sun.deploy.net.proxy.StaticProxyManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 18:34
 * @Description: 第6个线程池
 * 分叉 合并 线程， 和mapReduce 比较像
 * <p>
 * 把大任务 切分成一个个小任务，小任务还是大还可以再切分 怎么切分可以自己指定， 执行完毕后，在合并结果。
 * ForkJoinPool 也是一个守护线程 daemon
 */
public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int Max_Num = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        //直接求和
        System.out.println(Arrays.stream(nums).sum()); // stream api
    }

    // 分段求和 recursiveAction 和 recursionTask 的区别是前者没有返回值，后者有返回值
    static class AddTask extends RecursiveAction {
        int start;
        int end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= Max_Num) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from: " + start + " to: " + end + " = " + sum);
            } else {
                // 每次分到的任务长度大于max_num 就将这个任务在切成两个任务去运行计算
                int middle = start + (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    static class AddTask2 extends RecursiveTask<Long>{
        int start;
        int end;

        AddTask2(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if (end - start <= Max_Num) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
               return sum;
            } else {
                // 每次分到的任务长度大于max_num 就将这个任务在切成两个任务去运行计算
                int middle = start + (end - start) / 2;
                AddTask2 subTask1 = new AddTask2(start, middle);
                AddTask2 subTask2 = new AddTask2(middle, end);
                subTask1.fork();
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
//        AddTask task = new AddTask(0, nums.length);
        AddTask2 task = new AddTask2(0, nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);
       // System.in.read();
    }


}
