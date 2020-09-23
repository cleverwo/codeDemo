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
 */
public class Test28 {
    /**
     * hashmap遍历找一半
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
     * 答案2 摩尔投票
     * @param array
     */
    public static int MoreThanHalfNum_Solution3(int[] array){
        int x = 0, votes = 0;
        for(int num : array){
            if(votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }


    public static void main(String[] args) {
        int[] a = {1,2,3,2,2,2,5,4,2};
        System.out.println(MoreThanHalfNum_Solution3(a));
    }
}
