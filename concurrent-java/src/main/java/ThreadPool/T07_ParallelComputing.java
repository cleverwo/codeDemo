package ThreadPool;

import sun.nio.cs.FastCharsetProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 16:56
 * @Description:
 * 并行计算问题：
 */
public class T07_ParallelComputing {
    //并行计算求质数
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 普通单线程求质数花费的时间
        long start = System.currentTimeMillis();
        List<Integer> list = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        // 4个线程求质数的时间
        final int cpuCoreNum = 4;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        MyTask t1 = new MyTask(1,80000);
        MyTask t2 = new MyTask(80000,130000);
        MyTask t3 = new MyTask(130000,180000);
        MyTask t4 = new MyTask(180000,200000);
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get(); f2.get();f3.get();f4.get();
        end = System.currentTimeMillis();
        System.out.println(end-start);

    }

    static class MyTask implements Callable<List<Integer>> {
        int start,end;
        MyTask( int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> list = getPrime(start,end);
            return list;
        }
    }
    //判质数 只能是被1和自身整除的数
    static boolean isPrime(int num){
        for (int i=2;i<=num/2;i++){
            if (num%i==0){
                return false;
            }
        }
        return true;
    }
    //获取质数
    static List<Integer> getPrime(int start,int end){
        List<Integer> list = new ArrayList<>();
        for (int i=start;i<=end;i++){
            if (isPrime(i)){
                list.add(i);
            }
        }
        return list;
    }
}
