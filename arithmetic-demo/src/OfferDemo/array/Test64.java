package OfferDemo.array;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 12:17
 * @Description:
 * 64，滑动窗口的最大值
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
 * 他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 */
public class Test64 {

    /**
     * 思路：
     * 先比较size内，在用一个标记为最大，在往后遍历到数组结束，更新最大
     * 没有考虑滑窗出去的那个数
     * 解决：
     * 1.保存两个最大，最大出去的时候用第二大来比较,还是一样的问题
     * 2.用优先队列存存最大值
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num==null||num.length==0||size<=0){
            return list;
        }
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        //先找size内的
        if(size> num.length){
            size = num.length;
        }
        for (int i=0;i<size;i++){
            if (max<num[i]){
                second = max;
                max=num[i];
            }else {
                if (second<num[i]){
                    second = num[i];
                }
            }
        }
        list.add(max);
        //在遍历数组
        for (int i=size;i<num.length;i++){
            //先出元素，重置大小值
            if (num[i-size]==max){
                max = second;
                second = second-1;
            }
            //在如元素，比较大小值，赋值大小值
            if (max<num[i]){
                second = max;
                max=num[i];
            }else {
                if (second<=num[i]){
                    second = num[i];
                }
            }
            list.add(max);
        }
        return list;
    }
    public ArrayList<Integer> maxInWindows_0(int [] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (num==null||num.length==0||size<=0||size>num.length){
            return list;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            //规定队列从大到小排序
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i=0;i<size;i++){
            queue.offer(num[i]);
        }
        list.add(queue.peek());
        //在遍历数组
        for (int i=size;i<num.length;i++){
            //先出元素，重置大小值
            if (queue.peek().equals(num[i - size])){
                queue.poll();
            }
            //在如元素，比较大小值，赋值大小值
            queue.offer(num[i]);
            list.add(queue.peek());
        }
        return list;
    }

    public static void main(String[] args) {
        //[2,3,4,2,6,2,5,1],3
        //[16,14,12,10,8,6,4],5
        int[] a = {16,14,12,10,8,6,4};
        Test64 t = new Test64();
        ArrayList<Integer> list = t.maxInWindows_0(a,5);
        System.out.println(list.toString());

    }
}
