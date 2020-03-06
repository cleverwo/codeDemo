package container;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 13:42
 * @Description:
 * 卖票示例3
 */
public class TicketSeller3 {
    static List<String> tickets = new ArrayList<>();

    static{
        for (int i =0;i<100;i++){
            tickets.add("票ID"+i);
        }
    }

    public static void main(String[] args) {
        for (int i =0;i<10;i++){
            new Thread(()->{
                // 每次操作把整个list对象锁定
                synchronized(tickets){
                    while (tickets.size()>0){
                        try {
                            TimeUnit.MILLISECONDS.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(tickets.remove(0));
                    }
                }
            }).start();
        }
        //前两个示例报错是因为判断和操作remove分离，或者时remove不是原子操作，
        //这一对判断和操作枷锁，保证其原子性，这样就不报错了，但效率低
    }
}
