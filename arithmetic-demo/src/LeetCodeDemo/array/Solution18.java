package LeetCodeDemo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 16:26
 * @Description: 18 四数之和
 */
public class Solution18 {

    /**
     * 思路： 能不能用三树之和 基础上升级呢 想的很好在去重上错了
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0) {
            return list;
        }
        Arrays.sort(nums);
        //把问题变成三树之和
        int[] san = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            san[i] = target - nums[i];
        }
        //san数组里是nums坐标+ sum坐标等于target
        for (int j = 0; j < nums.length; j++) {
            target = san[j];
            for (int i = j + 1; i < nums.length; i++) {
                if (i > 1 && nums[i] == nums[i - 1]) continue;
                int s = i + 1;
                int e = nums.length - 1;
                while (s < e) {
                    if ((nums[s] + nums[e]) + nums[i] == target) {
                        List<Integer> node = new ArrayList<>();
                        node.add(nums[j]);
                        node.add(nums[i]);
                        node.add(nums[s]);
                        node.add(nums[e]);
                        list.add(node);
                        s++;
                        e--;
                        while (s < nums.length && nums[s] == nums[s - 1]) s++;
                        while (e > 0 && nums[e] == nums[e + 1]) e--;
                    } else if (nums[s] + nums[e] + nums[i] > target) {
                        e--;
                    } else {
                        s++;
                    }
                }
            }
        }
        return list;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
            int min = nums[i] + nums[i+1] + nums[i+2] + nums[i+3];
            if (min > target) {
                break;
            }
            /*获取当前最大值，如果最大值比目标值小，说明前面越来越小的值根本没戏*/
            int max = nums[i] + nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3];
            if (max < target){
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                /*获取当前最小值，如果最小值比目标值大，说明后面越来越大的值根本没戏*/
                int minn = nums[i] + nums[j] + nums[j+1] + nums[j+2];
                if (minn > target) {
                    break;
                }
                /*获取当前最大值，如果最大值比目标值小，说明前面越来越小的值根本没戏*/
                int maxx = nums[i] + nums[j] + nums[nums.length-1] + nums[nums.length-2];
                if (maxx < target){
                    continue;
                }
                int s = j + 1;
                int e = nums.length - 1;
                while (s < e) {
                    if (nums[s] + nums[e] + nums[j] + nums[i] == target) {
                        List<Integer> node = new ArrayList<>();
                        node.add(nums[i]);
                        node.add(nums[j]);
                        node.add(nums[s]);
                        node.add(nums[e]);
                        list.add(node);
                        s++;
                        e--;
                        while (s < nums.length && nums[s] == nums[s - 1]) s++;
                        while (e > 0 && nums[e] == nums[e + 1]) e--;
                    } else if (nums[s] + nums[e] + nums[j] + nums[i] > target) {
                        e--;
                    } else {
                        s++;
                    }
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        Solution18 s = new Solution18();
        int[] a = {-1, 0, 1, 2, -1, -4};
        System.out.println(s.fourSum(a, -1));
        // -2,-1,0,0,1,2
        // 2,1,0,0,-1,-2
    }

}
