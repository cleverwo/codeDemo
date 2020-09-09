package LeetCodeDemo.hard;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 15:19
 * @Description:
 * 42 接雨水
 */
public class S42 {

    // 动态规划解答 感觉不像动态规划
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        // 记录数组可能的左边界，用来确定是否可以形成积水
        int[] left = new int[height.length];
        int max = height[0];
        for (int i=0;i<height.length;++i){
            if (height[i]>max){
                max = height[i];
            }
            left[i] = max;
        }
        // 记录数组可能成为的有边界
        int[] right = new int[n];
        max = height[n-1];
        for (int i=n-1;i>=0;i++){
            if (height[i]>max){
                max = height[i];
            }
            right[i] = max;
        }
        // 比较左右边界和当前柱子的高度确定是否存在积水
        int ans = 0;
        for(int i=0;i<n;i++){
            int curr = Math.min(left[i],right[i]);
            if (curr > height[i]){
                ans += curr - height[i];
            }
        }
        return ans;

    }
}
