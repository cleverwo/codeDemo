package LeetCodeDemo.eassy;

/**
 * @Auther: 10413
 * @Date: 2020/8/31 23:07
 * @Description:
 * 198. 打家劫舍
 */
public class S198 {

    public int rob(int[] nums) {
        // 偷最高的在偷最低的
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int[] si = new int[nums.length];
        si[0] = nums[0];
        si[1] = Math.max(si[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            si[i] = Math.max(si[i-1],si[i-2]+nums[i]);
        }
        return si[nums.length-1];
    }

    public int rob1(int[] nums) {
        // 偷最高的在偷最低的
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int pre = nums[0];
        int p = Math.max(nums[0],nums[1]);
        for (int i=2;i<nums.length;i++){
            int temp = Math.max(p,pre+nums[i]);
            pre = p;
            p = temp;
        }
        return p;
    }
}
