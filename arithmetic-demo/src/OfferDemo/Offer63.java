package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/9/9 20:20
 * @Description:
 *
 * 股票的最大利润
 */
public class Offer63 {

    // 动态规划
    /*
        dp数组 i天的利润最大
        前i日最大利润 = max（前（i-1）日最大利润，第i日价格-前i日最小价格）
        dp[i] = max( dp[i-1], price[i] - min(0~i))
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0 ){
            return 0;
        }
        int[] dp = new int[prices.length+1];
        int min = Integer.MAX_VALUE;
        dp[0] = 0;
        for (int i=1;i<=prices.length;i++){
            min = Math.min(min,prices[i-1]);
            dp[i] = Math.max(dp[i-1],prices[i-1] - min);
        }
        return dp[prices.length];
    }

    //-----------------------------------

    public static void main(String[] args) {
        Offer63 o = new Offer63();
        int[] test = {7,1,5,3,6,4};
        System.out.println(o.maxProfit(test));
    }
}
