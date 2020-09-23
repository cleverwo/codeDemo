package OfferDemo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 10413
 * @Date: 2020/2/21 22:02
 * @Description:
 * 45.扑克牌顺子
 */
public class Test45 {

    // set 去重+数学推断
    public boolean isStraight(int[] nums){
        if (nums.length==0){
            return false;
        }
        Set<Integer> set = new HashSet<>();
        int offset = 0;
        int low = 13;
        int high = 1;
        for (int i=0;i<nums.length;i++){
            if (nums[i] == 0){
                offset++;
            }else{
                low = Math.min(low,nums[i]);
                high = Math.max(high,nums[i]);
                set.add(nums[i]);
            }
        }
        int n = nums.length;
        //没0 或 有0 但非0的还是连续,因为length就是5 //有0，非0的不连续，用0补齐
        if ((high-low)<n&&(offset+set.size()==n)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Test45 t = new Test45();
        System.out.println(t.isStraight(new int[]{0,0,1,2,5}));
    }
}
