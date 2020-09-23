package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/12 17:38
 * @Description:
 * 13 调整数组顺序使奇数位于偶数之前
 */
public class Test13 {

    /**
     * 排序算法，稳定排序
     * @param array
     */
    public static void reOrderArray(int [] array) {
        for (int i=0;i<array.length-1;i++){
            for (int j=0;j< array.length-1;j++){
               if ((array[j]&1) == 0){
                   int tmp = array[j];
                   array[j] = array[j+1];
                   array[j+1] = tmp;
               }
            }
        }
    }

    /**
     * 参考快速排序思想
     * 设立两个指针扫描全数组，找到对应奇偶的起始位置进行移动
     */
    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length -1;
        while(i<j){
            while((nums[i] & 1) == 1 && i<j) i++;
            while((nums[j] & 1) == 0 && i<j) j--;
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
