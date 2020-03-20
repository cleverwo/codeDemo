package LeetCodeDemo.array;

import LeetCodeDemo.link.Solution2;

/**
 * @Auther: 10413
 * @Date: 2020/3/19 22:00
 * @Description:
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 */
public class Solution34 {
    /**
     * 二分查找看似简单，，细节边界会让你爆炸
     */
    public int[] searchRange(int[] nums, int target) {
        int[] out = new int[]{-1,-1};
        if (nums==null||nums.length==0){
            return out;
        }
        int low = 0,high =nums.length-1;
        while (low<high){
            int mid = (low+high)/2;
            if (mid>target||mid == target){

            }
            if (target<nums[mid]){

            }
        }
        return out;
    }

    /**
     * 答案： 找到了也不结束，直到全部弄完 这道题里额一个帖子，整合的二分查找
     */
    // 查左侧边界
    public int searchLeft(int[] nums, int target) {
        if (nums==null||nums.length==0){
            return -1;
        }
        int low = 0;
        int high = nums.length; //这里不是length-1 注意
        while (low<high){ // 注意
            int mid = (low + high)/2;
            if (nums[mid]==target){
                high = mid;
            }else if(nums[mid]<target){
                low = mid + 1;
            }else if (nums[mid]>target){
                high = mid; // 注意
                //这个很好解释，因为我们的「搜索区间」是 [left, right) 左闭右开，所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，即 [left, mid) 或 [mid + 1, right)。
            }
        }
        // 这里的low的意思是比target小的数的个数，取值范围为[0-nums.length]
        return low;
        // target 比所有数都大
        //if (low == nums.length) return -1;
        // 类似之前算法的处理方式
        //return nums[low] == target ? low : -1;
    }

    public static void main(String[] args) {
        Solution34 s = new Solution34();
        int[] a = {1,3,3,3,3,3};
        System.out.println(s.searchLeft(a,1));
    }
}
