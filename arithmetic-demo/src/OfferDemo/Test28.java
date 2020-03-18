package OfferDemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 10413
 * @Date: 2020/2/18 10:44
 * @Description:
 * 28，数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，
 * 请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 */
public class Test28 {
    /**
     * 思路
     * 遍历找一半
     * @param array
     * @return
     */
    public static int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length;
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<length;i++){
            if (hashMap.containsKey(array[i])){
                int a = hashMap.get(array[i]);
                hashMap.put(array[i],++a);
            }else{
                hashMap.put(array[i],1);
            }
        }
        List<Integer> list = hashMap.keySet().stream().collect(Collectors.toList());
        for (Integer key : list ){
            if (hashMap.get(key) > (length/2)){
                return key;
            }
        }
        return 0;
    }

    /**
     * 答案1
     * 排序取中间数，判读中间数的次数
     * @param array
     */
    public int MoreThanHalfNum_Solution2(int [] array) {
        Arrays.sort(array);
        int mid = array[array.length/2];
        int n =0;
        for (int i=0;i<array.length;i++){
            if (array[i] == mid){
                n++;
            }
        }
        if (n>array.length/2){
            return mid;
        }
        return 0;
    }

    /**
     * 答案2 多数元素
     * @param array
     */
    public static int MoreThanHalfNum_Solution3(int[] array){
        int n=0;
        int ret=0;
        for (int i=0;i<array.length;i++){
            if (n==0){
                ret = array[i];
                n=1;
            }else{
                if (ret == array[i]){
                    n++;
                }else{
                    n--;
                }
            }
        }
        int count = 0;
        for (int i=0;i<array.length;i++){
            if (ret == array[i]){
                count ++;
            }
        }
        if (count > array.length/2){
            return ret;
        }
        return 0;
    }


    public static void main(String[] args) {
        int[] a = {1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalfNum_Solution3(a));
    }
}
