package OfferDemo;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 18:03
 * @Description:
 * 50，数组中重复的数字
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 */
public class Test50 {

    /**
     *hash求
     * @param numbers 输入的数字
     * @param length 数组长度
     * @param duplication 第一个重复的数字存到【0】里
     * @return true 存在重复，false不存在
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers==null||length==0){
            return false;
        }
        int[] nums = new int[length];
        boolean flag = false;
        for (int i=0;i<length;i++){
            if (nums[numbers[i]]>0){
                duplication[0] = numbers[i];
                flag = true;
                break;
            }
            nums[numbers[i]]++;
        }
        return flag;
    }

    public boolean duplicate1(int numbers[],int length,int[] duplication){
        if(numbers==null||numbers.length==0){
            return false;
        }
        int[] num = new int[length];
        boolean flag = false;
        for(int i=0;i<length;i++){
            if(num[numbers[i]]!=0){
                duplication[0]=numbers[i];
                flag = true;
                break;
            }else{
                num[numbers[i]]++;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        Test50 t = new Test50();
        int[] a = {2,4,2,1,4};
        int[] b = new int[1];
        t.duplicate1(a,a.length,b);
        System.out.println(Arrays.toString(b));
    }
}
