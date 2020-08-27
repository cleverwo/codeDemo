package LeetCodeDemo.eassy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 22:03
 * @Description: 448. 找到所有数组中消失的数字
 */
public class S448 {

    // 原地修改
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i=0;i< nums.length;i++){
            int n = Math.abs(nums[i]);
            if (nums[n-1]>0){
                nums[n-1] *= -1;
            }
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]>0){
                list.add(i+1);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        S448 s = new S448();
        int[] a = {4,3,2,7,8,2,3,1};
        s.findDisappearedNumbers(a);
    }

}
