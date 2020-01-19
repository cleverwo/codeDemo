package LeetCodeDemo;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/19 22:46
 * @Description:
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Solution4 {

    /**
     * 暴力法 时间：O(n²) 空间：O(1)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                if (nums[j] == target - nums[i]){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * 一遍哈希表
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。
     * 如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     * 通过以空间换取速度的方式，我们可以将查找时间从 O(n)降低到 O(1)。
     * 哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。
     * 我用“近似”来描述，是因为一旦出现冲突，查找用时可能会退化到 O(n)。
     * //TODO 哈希表的原理？？
     * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)。
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
     *
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
        int[] a = {2,7,11,12};
        int[] result = twoSum3(a,9);
        System.out.println(Arrays.toString(result));
    }
}
