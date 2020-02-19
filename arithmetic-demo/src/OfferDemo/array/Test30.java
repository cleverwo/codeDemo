package OfferDemo.array;

/**
 * @Auther: 10413
 * @Date: 2020/2/18 16:11
 * @Description: 30，连续子数组的最大和
 * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,
 * 他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
 * 当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
 * 并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8
 * (从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，
 * 你会不会被他忽悠住？(子向量的长度至少是1)
 * <p>
 * 求数组可能的最大值
 */
public class Test30 {

    /**
     * 可以利用28题的思路吗
     * {6,-3,-2,7,-15,1,2,2}
     * //TODO 没有好思路
     *
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1) {
            return array[0];
        }
        int sum = 0;
        return 0;
    }

    /**
     * 答案1：
     * 这题目应该是最基础的动态规划的题目：
     * 最大子数组的和一定是由当前元素和之前最大连续子数组的和叠加在一起形成的，
     * 因此需要遍历n个元素，看看当前元素和其之前的最大连续子数组的和能够创造新的最大值
     *
     * 从前往后遍历，最大的连续子序列的和是由当前元素和之前的最大连续子序列的和叠加在一起形成的。
     * 如果之前的最大连续子序列的和大于零，我们可以继续累加，如果小于零，则需要舍去之前的子序列，
     * 重新从当前的数字开始累加。时间复杂度为O(n)
     *
     * dp[n]代表以当前元素为截止点的连续子序列的最大和，
     * 如果dp[n-1]>0，dp[n]=dp[n]+dp[n-1]，因为当前数字加上一个正数一定会变大；
     * 如果dp[n-1]<0，dp[n]不变，因为当前数字加上一个负数一定会变小。
     * 使用一个变量max记录最大的dp值返回即可。
     */
    public int FindGreatestSumOfSubArray1(int[] array) {
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
}
