package LeetCodeDemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/19 22:46
 * @Description:
 * 1. 两数之和
 */
public class Solution1 {

    /**
     * 一遍哈希表
     * @return
     */
    public static int[] twoSum2(int[] nums,int target){
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int result = target - nums[i];
            if (hashMap.containsKey(result)){
                return new int[]{hashMap.get(result),i};
            }else {
                hashMap.put(nums[i],i);
            }
        }
        return null;
    }

    /**
     *  与位运算
     * @return
     */
    public static int[] twoSum3(int[] nums,int target){
        int indexArrayMax = 2047;
        int[] indexArrays = new int[indexArrayMax +1];
        for (int i=0;i<nums.length;i++){
            int diff = target - nums[i];
            int index = diff & indexArrayMax;
            if (indexArrays[index] !=0){
                return new int[]{indexArrays[index]-1,i};
            }
            indexArrays[nums[i]&indexArrayMax] = i+1;
        }
        throw new  IllegalArgumentException("no result");
    }

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int[] a = {2,7,11,12};
        int[] result = twoSum2(a,9);
        System.out.println(Arrays.toString(result));
        AtomicBoolean atomicBoolean = new AtomicBoolean();
    }
}
