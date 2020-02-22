package OfferDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/2/21 10:54
 * @Description: 42，和为S的两个数
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
 * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
 * <p>
 * 对应每个测试案例，输出两个数，小的先输出。
 */
public class Test42 {

    /**
     * 思路：
     * 利用一个hashmap
     *
     * @param array
     * @param sum
     * @return
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if (array == null || array.length == 0) {
            return resultList;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int large = array[array.length - 1];
        for (int i = 0; i < array.length; i++) {
            int diff = sum - array[i];
            if (diff <= large) {
                map.put(diff, array[i]);
            }
        }
        int sumR = Integer.MAX_VALUE;
        int result = 0;
        int flag = 0;
        List<Integer> keyList = new ArrayList<>(map.keySet());
        for (Integer key : keyList) {
            int value = map.get(key);
            if (map.containsKey(value)) {
                int product = key * value;
                if (sumR > product) {
                    sumR = product;
                    result = key > value ? value : key;
                    flag = 1;
                }
            }
        }
        if (flag == 1) {
            resultList.add(result);
            resultList.add(map.get(result));
        }
        return resultList;
    }

    /**
     * 答案1：
     * 同样利用双指针，不同41题的是，指针的起始点为开头和结尾
     *
     * @param array
     * @param sum
     * @return
     */
    /**
     *     if(array.length<2) return [];
     *     var slow=0;var high = array.length-1;var list = [];
     *     while(slow<high){
     *     var current = array[slow]+array[high];
     *     if(current<sum){
     *         slow++
     *     }else if(current>sum){
     *         high--;
     *     }else if(current==sum){
     *         list.push(array[slow]);
     *         list.push(array[high]);
     *         break;
     *     }
     *     }
     *
     *     return list;
     *     */
    public ArrayList<Integer> FindNumbersWithSum1(int[] array, int sum) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        if (array.length < 2) {
            return arrayList;
        }
        int slow = 0,high = array.length-1;
        while(slow<high){
            int current = array[slow]+array[high];
            if (current<sum){
                slow++;
            }else if(current>sum){
                high--;
            }else{
                arrayList.add(array[slow]);
                arrayList.add(array[high]);
                break;
            }
        }
        return arrayList;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 7, 11, 15};
        Test42 t = new Test42();
        ArrayList<Integer> list = t.FindNumbersWithSum(a, 15);
    }
}
