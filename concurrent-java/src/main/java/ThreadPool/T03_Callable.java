package ThreadPool;

import java.util.concurrent.Callable;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 15:59
 * @Description:
 * Callable 任务
 * 和Runnable区别：
 *
 * callable 有返回值， 且可以抛出一个异常
 * runnable 没有返回值， 不能抛出异常。
 *
 */
public class T03_Callable {

    public static void main(String[] args) {

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        };
    }
}
