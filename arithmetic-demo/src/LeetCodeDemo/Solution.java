package LeetCodeDemo;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/15 16:37
 * @Description:  LeetCode 题库
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 */
public class Solution {
    /**
     *  给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *  不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * @param nums
     * @return 去重后数组的长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int same = nums[0];
        int x = 1;
        for(int i=1;i<nums.length;i++){
            if (same != nums[i]){
                same = nums[i];
                nums[x] = same;
                x = x+1;
            }
        }
        return x;
    }

}
