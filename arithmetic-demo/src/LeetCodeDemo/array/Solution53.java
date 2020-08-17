package LeetCodeDemo.array;

/**
 * @Auther: 10413
 * @Date: 2020/4/13 17:21
 * @Description:
 * 53. 最大子序和
 */
public class Solution53 {

    /**
     * 暴力法，这个对，但时间复杂度时n三次方
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        //遍历数组
        for (int i=1;i<=n;i++){
            for (int j=0;j<=n-i;j++){
                int curr = 0;
                for (int k=j;k<j+i;k++){
                    curr += nums[k];
                    max = Math.max(curr,max);
                }
            }
        }
        return max;
    }

    public static int maxSubArray1(int[] nums){
        return helper(nums, 0, nums.length - 1);
    }

    public static int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];
        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }
        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for(int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }
        return leftSubsum + rightSubsum;
    }
    public static int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int p = (left + right) / 2;
        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);
        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }
    public int maxSubArray2(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        int[] a = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray1(a));
    }
}

