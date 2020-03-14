package LeetCodeDemo.array;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 10:28
 * @Description:
 * 15, 三数之和
 */
public class Solution15 {

    /**
     * 无法保证去重，且一个一个实验也很麻烦
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<nums.length-2;i++){
            int sum = nums[i];
            stack.push(sum);
            for (int j=i+1;j<nums.length-1;j++){
                sum += nums[j];
                stack.push(nums[j]);
                for (int k=j+1;k<nums.length;k++){
                    sum += nums[k];
                    stack.push(nums[k]);
                    if (sum == 0){
                        result.add(new ArrayList(stack));
                        stack.pop();
                        sum -= nums[k];
                        break;
                    }
                    stack.pop();
                    sum -= nums[k];
                }
                stack.pop();
                sum -= nums[j];
            }
            stack.pop();
        }
        return result;
    }

    /**
     * 答案： 快排 + 2sum
     * 利用2sum的思路，就是2数之和为 -2sum
     */
    public List<List<Integer>> threeSum2(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0){
            return result;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            int target = -nums[i];
            if (i>0 && nums[i] == nums[i-1]) continue;
            int k = nums[i+1];
            int j = nums[nums.length-1];
            while(k<j){
                if (nums[j]+nums[k]==target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                    j--;k++;
                    //去重
                    while(k<nums.length&&nums[k]==nums[k-1]) k++;
                    while(j>0&&nums[j]==nums[j+1]) j--;
                }else if(nums[j]+nums[k]>target){
                    k++;
                }else{
                    j--;
                }
            }
        }
        return result;

    }

    public static void main(String[] args) {
        Solution15 s = new Solution15();
        int[] a = {-1, 0, 1, 2, -1, -4};
        System.out.println(s.threeSum(a));
    }
}
