package LeetCodeDemo.array;


import jdk.nashorn.internal.ir.Flags;
import sun.security.util.Length;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/3/19 10:24
 * @Description:
 * 31. 下一个排列
 */
public class Solution31 {

    /**
     * 求除了第一个数后的升序排序，冒泡求解，定义换位flag
     */
    public void nextPermutation(int[] nums) {
        if (nums==null||nums.length==0){
            return;
        }
        //从第二位开始
        boolean isMax = false;
        for (int i=0;i<nums.length-1;i++){
            boolean flag = true;
            for(int j=1;j<nums.length-i-1;j++){
                if (nums[j]>nums[j+1]){
                    int swap = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = swap;
                    flag = false;
                    isMax = true;
                }
            }
            if (flag){
                break;
            }
        }
        if (!isMax){
            Arrays.sort(nums);
        }
    }

    /**
     * 答案： 全排序，字典排序
     * 1.找正序对i 2.从i往后找比i大的最小数j 3.交换i，j 反转i+1到末尾
     */
    public void nextPermutation1(int[] nums){
        int i = nums.length-2; //倒数第2个数开始找
        while (i>=0&&nums[i+1]<=nums[i]){
            i--;
        }
        if(i<0){
            //没有找到正序对，证明数组为升序，则从小到大排序，输出
            Arrays.sort(nums);
        }else{
            //找到i了，开始进行2，3步
            int j = nums.length-1;
            //从后往前找比i大的最小数，因为i往后的数一定是降序的，因为i是第一个正序的数，所以i往后的是降序的，
            // 从右到左，如果i>=j 则 j--，j停留在i<j的位置上
            for(; j>=0 && nums[j] <= nums[i]; j--){}
            int swap = nums[j];
            nums[j] = nums[i];
            nums[i] = swap;
            //反转i+1 到length-1
            for(int a=i+1,b=nums.length-1;a<b;a++,b--){
                swap = nums[a];
                nums[a] = nums[b];
                nums[b] = swap;
            }
        }
    }
}
