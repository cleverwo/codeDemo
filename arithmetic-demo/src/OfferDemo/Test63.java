package OfferDemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 11:12
 * @Description:
 * 63,数据流种的中位数
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
 * 使用GetMedian()方法获取当前读取数据的中位数。
 */
public class Test63<x> {

    /**
     * 思路：
     * 用有序的数组存数组留，在取数组的中值输出
     */
    static ArrayList<Integer> list = new ArrayList<>();
    public static void Insert(Integer num) {
        if (list.size()==0){
            list.add(num);
        }else{
            int size = list.size();
            int flag = 0;
            for (int i=0;i<size;i++){
                if (num<list.get(i)){
                    list.add(i,num);
                    flag =1;
                    break;
                }
            }
            if (flag==0){
                list.add(num);
            }
        }
    }
    public static Double GetMedian() {
        if (list.size()==0){
            return 0.0;
        }
        int size = list.size();
        if (size%2==0){
            int mid = size/2;
            double r1 = Double.valueOf(list.get(mid));
            double r2 = Double.valueOf(list.get(mid-1));
            return (r1+r2)/2;
        }else{
            int mid = size/2+1;
            return (double)list.get(mid-1);
        }
    }

    /**
     * 答案：
     * 利用小根堆存数据
     */
    private int cnt = 0;
    private PriorityQueue<Integer> low = new PriorityQueue<>();
    // 默认维护小顶堆
    private PriorityQueue<Integer> high = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    public void Insert1(Integer num) {
        //大顶堆存放前半段，小的数，小顶堆存放后半段 大的数
        // 奇数个优先存到小顶堆中
        // 数量++
        cnt++;
        // 如果为奇数的话
        if ((cnt & 1) == 1) {
            // 由于奇数，需要存放在大顶堆上
            // 但是呢，现在你不知道num与小顶堆的情况
            // 小顶堆存放的是后半段大的数
            // 如果当前值比小顶堆上的那个数更大
            if (!low.isEmpty() && num > low.peek()) {
                // 存进去
                low.offer(num);
                // 然后在将那个最小的吐出来
                num = low.poll();
            } // 最小的就放到大顶堆，因为它存放前半段
            high.offer(num);
        } else {
            // 偶数的话，此时需要存放的是小的数
            // 注意无论是大顶堆还是小顶堆，吐出数的前提是得有数
            if (!high.isEmpty() && num < high.peek()) {
                high.offer(num);
                num = high.poll();
            } // 大数被吐出，小顶堆插入
            low.offer(num);
        }

    }
    public Double GetMedian1() {// 表明是偶数
        double res = 0;
        // 奇数
        if ((cnt & 1) == 1) {
            res = high.peek();
        } else {
            res = (high.peek() + low.peek()) / 2.0;
        }
        return res;
    }

    PriorityQueue<Integer> lows = new PriorityQueue<>();
    PriorityQueue<Integer> highs = new PriorityQueue<>((x,y)->{return y-x;});
    int count = 0;
    public void insertNum(int num){
        count++;
        if ((count & 1) == 1){
            //奇数
            // 小的存大的数，看看这个是不是大数
            if (!lows.isEmpty() && num > lows.peek()){
                int tmp = lows.poll();
                lows.offer(num);
                num = tmp;
            }
            highs.offer(num);
        }else{
            // 优先存了大顶堆，则偶数存在小顶堆
            if (!highs.isEmpty() && num < highs.peek()){
                int tmp = highs.poll();
                highs.offer(num);
                num = tmp;
            }
            low.offer(num);
        }
    }

    public double result(){
        if ((count & 1) == 1){
            return (double)highs.peek();
        }else{
            double a = highs.peek();
            double b = lows.peek();
            return a + (b-a)/2;
        }
    }



    public static void main(String[] args) {
        //5,2,3,4,1,6,7,0,8
        Insert(1);
        System.out.println(GetMedian());
        Insert(2);
        System.out.println(GetMedian());
        Insert(3);
//        System.out.println(GetMedian());
//        Insert(4);
//        System.out.println(GetMedian());
//        Insert(1);
//        System.out.println(GetMedian());
//        Insert(6);
//        System.out.println(GetMedian());
//        Insert(7);
//        System.out.println(GetMedian());
//        Insert(0);
//        System.out.println(GetMedian());
//        Insert(8);
//        System.out.println(GetMedian());
    }
}
