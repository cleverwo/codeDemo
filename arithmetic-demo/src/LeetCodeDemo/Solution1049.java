package LeetCodeDemo;

import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/21 21:14
 * @Description:
 * 1049. 最后一块石头的重量 II
 */
public class Solution1049 {

    public int lastStoneWeightII(int n,int[] stones) {
        int sum = 0;
        for(int stone:stones){
            sum += stone;
        }
        int[] dp = new int[(sum >> 1) + 1];
        for(int stone:stones){
            zeroOnePack(dp, stone, stone);
        }
        return sum - (dp[(sum >> 1)] << 1);
    }

    private void zeroOnePack(int[] dp, int cost, int val){
        for(int i = dp.length - 1; i >= cost; i--){
            dp[i] = Math.max(dp[i], dp[i - cost] + val);
        }
    }

    public static void main(String[] args) {
        Solution1049 ss  = new Solution1049();
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int[] stones = new int[n];
        for (int i=0;i<n;i++){
            stones[i] = c.nextInt();
        }
//        int t = 11;
//        int[] b = {321,900,972,910,478,730,814,543,887,824,831};
        System.out.println(ss.lastStoneWeightII(n,stones));
        //11
        //321 900 972 910 478 730 814 543 887 824 831
    }
}
