package container;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 13:32
 * @Description:
 * 售票的实现，引出高并发容器
 *
 */
public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();

    static{
        for (int i =0;i<10000;i++){
            tickets.add("票ID"+i);
        }
    }

    public static void main(String[] args) {
        // 十个线程去卖票，当tickets不是0就减去一张票
        for (int i =0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){
                    tickets.remove(0);
                }
            }).start();
        }
        //肯定报错，tickets 的判断和remove中间可能有其他线程的重入，
        //list 的remove 方法不是原子性操作，其中会有其他线程重入。
    }
}
