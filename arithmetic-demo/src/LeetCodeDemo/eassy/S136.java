package LeetCodeDemo.eassy;

import java.security.Signature;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 12:53
 * @Description:
 * 只出现一次的数字
 */
public class S136 {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else{
                map.put(nums[i],1);
            }
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }

    public int singleNumber1(int[] nums) {
        int single = 0;
        for (Integer num : nums){
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        S136 s = new S136();
        int[] test = {1,1,2,2,3,3,4,5,5};
        System.out.println(s.singleNumber1(test));
    }
}
