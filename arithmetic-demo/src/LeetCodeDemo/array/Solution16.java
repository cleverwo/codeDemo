package LeetCodeDemo.array;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 11:38
 * @Description:
 * 16，最接近的三数之和
 */
public class Solution16 {

    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3){
            return -1;
        }
        Arrays.sort(nums);
        //初始化最接近的值
        int min = nums[0]+nums[1]+nums[2];
        for(int i=0;i<nums.length;i++){
            int s = i+1;
            int e = nums.length-1;
            while(s<e){
                int curr = nums[i] + nums[s] + nums[e];
                if (Math.abs(target-min) > Math.abs(target-curr)){
                    min = curr;
                }
                if (curr>target){
                    e--;
                }else if(curr < target){
                    s++;
                }else{
                    return min;
                }
            }
        }
        return min;
    }
}
