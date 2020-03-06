package container;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 10413
 * @Date: 2020/3/5 13:37
 * @Description:
 * 卖票 的第2个示例
 */
public class TicketSeller2 {
    static Vector<String> tickets = new Vector<>();

    static{
        for (int i =0;i<100;i++){
            tickets.add("票ID"+i);
        }
    }

    public static void main(String[] args) {
        for (int i =0;i<10;i++){
            new Thread(()->{
                while (tickets.size()>0){
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(tickets.remove(0));
                }
            }).start();
        }
        //Vector是一个同步容器，效率慢，不常用，这里remove是原子性操作了
        //还是报错，tickets 的判断和remove操作时分离的，所以判读后在没有remove前还是
        //被其他线程重入打断，在最后一张票时会多卖
    }
}
