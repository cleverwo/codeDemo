package LeetCodeDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/20 20:00
 * @Description:
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class Solution283 {

    /**
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int index = 0;
        for (int i=0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        for (; index < nums.length; index++) {
            nums[index] = 0;
        }
    }
}
