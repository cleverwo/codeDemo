package LeetCodeDemo.eassy;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 20:46
 * @Description:
 */
public class S169 {

    public int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer,Integer> map = new TreeMap<>();
        for (int i = 0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int value = list.get(0).getValue();
        if (value< (len/2)){
            return  -1;
        }
        return list.get(0).getKey();
    }

    // 取巧了，众数出现的次数一定大于n/2，排序取n/2的位置即可
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    //分治算法
    // nums数组lo-hi中 num 出现的次数
    private int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i <= hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
    // nums数组中 lo-hi的众数
    private int majorityElementRec(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }
        int mid = (hi-lo)/2 + lo;
        // 左边的众数
        int left = majorityElementRec(nums, lo, mid);
        // 右边的众数
        int right = majorityElementRec(nums, mid+1, hi);
        // 相等 那么就是众数
        if (left == right) {
            return left;
        }
        // 不相等 计算出现的次数 取最大值
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }
    public int majorityElement3(int[] nums) {
        return majorityElementRec(nums,0,nums.length-1);
    }

    public static void main(String[] args) {
        S169 s = new S169();
        int[] nums = {3,2,3};
        int a = s.majorityElement3(nums);
        System.out.println(a);
    }
}
