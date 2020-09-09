package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/18 16:11
 * @Description: 30，连续子数组的最大和
 */
public class Test30 {

    /**
     * 答案1：
     * dp[n]代表以当前元素为截止点的连续子序列的最大和，
     * 如果dp[n-1]>0，dp[n]=dp[n]+dp[n-1]，因为当前数字加上一个正数一定会变大；
     * 如果dp[n-1]<0，dp[n]不变，因为当前数字加上一个负数一定会变小。
     * 使用一个变量max记录最大的dp值返回即可。
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        int len = array.length;
        //当前长度的向量值
        int[] dp = new int[len];
        //max 当前最大的向量值 dp里的max
        int max = array[0];
        dp[0] = array[0];
        //从第2位开始，依次获取到其前i项的向量值的和
        for (int i = 1; i < len; i++) {
            //向量值的和
            int newMax = dp[i - 1] + array[i];
            //如果前i项和 比但前向量还小，则存当前向量
            if (newMax > array[i]) {
                dp[i] = newMax;
            } else {
                dp[i] = array[i];
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
    // 简化动规 为 滚动数组
    public int test1(int[] array){
        int len = array.length;
        int max = array[0];
        //当前下标之前的连续最大值 用tmp变量来代替数组dp
        int tmp = max;
        for (int i =1;i<len;i++){
            int newMax = tmp +array[i];
            if (newMax > array[i]){
                tmp = newMax;
            }else {
                tmp = array[i];
            }
            if (tmp > max){
                max = tmp;
            }
        }
        return max;
    }
    //---------------------------------------------
    public static void main(String[] args) {
        Test30 t = new Test30();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(t.FindGreatestSumOfSubArray(nums));
    }

}
