package LeetCodeDemo;

import java.util.Arrays;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/16 23:07
 * @Description:
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 */
public class Solution189 {

    /**
     * 解法1 暴力破解
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        //顺序移动
        k %=nums.length;
        int len = nums.length;
        for (int i=0;i < k; i++){
            int mid = nums[len-1];
            for (int j=len-2;j>=0;j--){
                nums[j+1] = nums[j];
            }
            nums[0] = mid;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 解法2 反转法
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums,int k){
        //一部到位
        k %= nums.length;
        reserve(nums,0,nums.length-1);
        reserve(nums,0,k-1);
        reserve(nums,k,nums.length-1);
        System.out.println(Arrays.toString(nums));

    }
    public static void reserve(int[] nums, int start, int end){
        int tmp = 0;
        while (start < end){
            tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start ++;
            end -- ;
        }
    }

    /**
     * 解法3 暴力一部到位
     * @param nums
     * @param k
     */
    public static void rotate3(int[] nums,int k){
        k %= nums.length; // 真正需要移动的位数
        int len = nums.length;
        for (int i=0;i<k;i++){
            int j = i;
            int mid = nums[j];
            int s = (j+k)%len;
            while (i != s){
                int tmp = mid;
                mid = nums[s];
                nums[s] = tmp;
                j = s;
                s = (j+k)%len;
            }
            nums[i] = mid;
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] num1 = {1,2,3,4,5,6,7};
        int[] num2 = {1,2,3,4,5,6,7};
        rotate3(num1,3);
        rotate(num2,3);

    }
}
