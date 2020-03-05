package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 22:54
 * @Description:
 *
 * 不属于线程池的东西
 *
 */
public class T14_ParallelStreamAPI {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i=0;i<10000;i++){
            nums.add(1000000 + r.nextInt(1000000));
        }

        long start = System.currentTimeMillis();
        nums.forEach(v->isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        //使用 parallelStream 多线程运行forEach
        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();
        System.out.println(end -start);
    }

    static boolean isPrime(int num){
        for (int i=2;i<=num/2;i++){
            if (num%i==0){
                return false;
            }
        }
        return true;
    }
}
