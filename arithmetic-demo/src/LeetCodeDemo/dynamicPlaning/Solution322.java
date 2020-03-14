package LeetCodeDemo.dynamicPlaning;

/**
 * @Auther: 10413
 * @Date: 2020/3/8 22:21
 * @Description:
 * 零钱兑换 322题
 *
 */
public class Solution322 {
    /**
     * 有问题，输入{186,419,83,408} 6249 有误
     */
    public int coinChange(int[] coins, int amount) {
        //找出小于count的最大硬币
        //count - 前一个值，
        //继续这样操作，直到count 0；
        //找小于count的最大硬币
        int count = 0;
        while(amount > 0){
            amount = getMaxCoin(coins,amount);
            System.out.println(amount);
            if(amount>=0){
                count++;
            }else{
                count = -1;
            }
        }
        return count;
    }
    public int getMaxCoin(int[] coins,int amount){
        int index=-1;
        int min = Integer.MAX_VALUE;
        int[] mid = new int[coins.length];
        for(int i=0;i<coins.length;i++){
            int cha = amount - coins[i];
            mid[i] = cha;
        }
        for(int i=0;i<mid.length;i++){
            if(mid[i]==0){
                index = i;
                break;
            }else if(mid[i]>0&&mid[i]<min){
                min = mid[i];
                index = i;
            }
        }
        if(index>=0){
            return amount - coins[index];
        }else{
            return -1;
        }
    }

    /**
     * 答案：自上而下
     */
    public int coinChange1(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /**
     *自下而上
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        int[] dp = new int[amount+1];
        dp[0]= 0;
        for (int a = 1; a<=amount;a++){
            int min = Integer.MAX_VALUE;
            for (int i=0;i<coins.length;i++){
                int n = a-coins[i];
                if (n>=0){
                    if (dp[n]!=-1 && dp[n]<min){
                        min = dp[n];
                    }
                }
            }
            dp[a]=min==Integer.MAX_VALUE?-1:min+1;
        }
        return dp[amount];
    }



    public static void main(String[] args) {
        Solution322 s = new Solution322();
        int[] a = {3,5,2};
        int i = s.coinChange2(a,8);
        System.out.println(i);
    }
}
