package LeetCodeDemo.eassy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/9/1 10:37
 * @Description: 581. 最短无序连续子数组
 */
public class S581 {

    public int findUnsortedSubarray(int[] nums) {
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    r = Math.max(r, j);
                    l = Math.min(l, i);
                }
            }
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

    public int findUnsortedSubarray1(int[] nums){
        int[] num = nums.clone();
        Arrays.sort(num);
        int s = nums.length,e = 0;
        for (int i=0;i<num.length;i++){
            if (num[i]!=nums[i]){
                s = Math.min(s,i);
                e = Math.max(e,i);
            }
        }
        return e-s>0?e-s+1:0;
    }

    public static void main(String[] args) {
        int[] t = {1,3,2,2};
        S581 s = new S581();
        System.out.println(s.findUnsortedSubarray(t));
    }
}
