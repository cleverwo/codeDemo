package container;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 13:46
 * @Description:
 * 使用高并发容器卖票
 *
 */
public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static{
        for (int i =0;i<100;i++){
            tickets.add("票ID"+i);
        }
    }

    public static void main(String[] args) {
        for (int i =0;i<10;i++){
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if (s!=null){
                        System.out.println(s);
                    }else{
                        break;
                    }
                }
            }).start();
        }
        // concurrentLinkedQueue 是同步容器，内部枷锁了，这里的poll操作时同步操作
        // 取出的s要不是空，要不不是空，所以后面的判断是对这个同步的值进行判断，所以不报错
        // 这里的 判断中没有操作队列，只是读多以不报错。
    }
}
