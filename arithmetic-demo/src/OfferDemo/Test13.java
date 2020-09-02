package OfferDemo;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/2/12 17:38
 * @Description:
 * 13 调整数组顺序使奇数位于偶数之前
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Test13 {

    /**
     * 排序算法，稳定排序
     * @param array
     */
    public static void reOrderArray(int [] array) {
        for (int i=0;i<array.length-1;i++){
            for (int j=0;j< array.length-1;j++){
               if ((array[j]&1) == 0){
                   int tmp = array[j];
                   array[j] = array[j+1];
                   array[j+1] = tmp;
               }
            }
        }
    }

    /**
     * 建队列 时间复杂度O(n) 空间复杂度O(n)
     * 最笨的方法了
     * @param array
     */
    public static void reOrderArray1(int[] array){
        //建立两个队列，奇数队，偶数队
        Queue<Integer> odd = new LinkedList<>();
        Queue<Integer> even = new LinkedList<>();
        // 插入队列
        for (int i=0;i<array.length;i++){
            if ((array[i]&1)==0){
                even.offer(array[i]);
            }else{
                odd.offer(array[i]);
            }
        }
        //重置到数组中
        int i=0;
        while (odd.size() !=0){
            array[i++] = odd.poll();
        }
        while (even.size() != 0){
            array[i++] = even.poll();
        }

    }

    /**
     * 建数组不用队列
     * @param array
     */
    public static void reOrderArray1_1(int[] array){
        List<Integer> odd = new ArrayList<>();
        List<Integer> even = new ArrayList<>();
        for (int i=0;i<array.length;i++){
            if ((array[i]&1)==0){
                even.add(array[i]);
            }else{
                odd.add(array[i]);
            }
        }
        int i=0,j=0;
        while (j<odd.size()){
            array[i++] = odd.get(j++);
        }
        j=0;
        while (j<even.size()){
            array[i++] = even.get(j++);
        }
    }

    /**
     * 参考快速排序思想
     * 设立两个指针扫描全数组，找到对应奇偶的起始位置进行移动
     *
     * @param array
     */
    public static void reOrderArray2(int[] array){
        // i指针指向偶数，j指针寻找奇数
        int i=0,j=i+1;
        //确定i，j的起始位置
        while (i<array.length&&(array[i]&1)==1){
                i++;j=i+1;
        }
        //j指向偶数最后一位
        while (j<array.length){
            if((array[j]&1)==0){
                j++;
            }else{
                //确定了i~j位的数组的最终位，将i-j-1位移
                int tmp = array[j];
                for(int a=j-1;a>=i;a--){
                    array[a+1]=array[a];
                }
                array[i] = tmp;
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1};
        reOrderArray2(a);
        System.out.println(Arrays.toString(a));
    }
}
