package LeetCodeDemo;

import java.util.Arrays;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/18 17:38
 * @Description:
 * 88. 合并两个有序数组
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Solution3 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //先放大的在放小的
        int a = m-1,b=n-1,len = nums1.length-1;
        while (a>=0&&b>=0){
            if(nums1[a]>nums2[b]){
                nums1[len] = nums1[a];
                a--;
            }else{
                nums1[len]=nums2[b];
                b--;
            }
            len --;
        }
        if (b>=0){
            for (int i=0;i<=b;i++){
                nums1[i]=nums2[i];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,0,0,0};
        int[] b = {2,5,6};
        merge(a,3,b,3);
    }
}
