package OfferDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 15:53
 * @Description:
 * 中等 数组中数字出现的次数
 */
public class Offer56_I {

    // 简单方法 HashMap
    public int[] singleNumbers(int[] nums) {
        if(nums.length==0){
            return new int[]{};
        }
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                map.remove(nums[i]);
            }else{
                map.put(nums[i],1);
            }
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        int[] result = new int[list.size()];
        for (int i=0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }
    // -----------------------------------------------

    // 分组异或
}
