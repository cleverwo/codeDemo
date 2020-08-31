package LeetCodeDemo.eassy;

import sun.security.util.Length;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/8/27 23:28
 * @Description: 121. 买卖股票的最佳时机
 */
public class S121 {
    Map<Integer, Integer> map = new HashMap<>();

    public int maxProfit(int[] prices) {

        for (int i = 0; i < prices.length; ++i) {
            map.put(prices[i], i);
        }
        int L = 0, H = prices.length-1;
        Arrays.sort(prices);
        while (map.get(prices[H]) < map.get(prices[L])) {
            H--;
        }
        int result1 = prices[H] - prices[L];
        L = 0;
        H = prices.length-1;
        while (map.get(prices[H]) < map.get(prices[L])) {
            L++;
        }
        int result2 = prices[H] - prices[L];
        return result1 > result2 ? result1 : result2;
    }


    public int maxProfit2(int[] prices){
        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i=0;i<prices.length;++i){
            if (prices[i]<min){
                min = prices[i];
            }
            int curr = prices[i] - min;
            if (curr > result){
                result = curr;
            }
        }
        return result;
    }



    public static void main(String[] args) {
        S121 s = new S121();
        System.out.println(s.maxProfit2(new int[]{7,1,5,3,6,4}));
        System.out.println(s.maxProfit2(new int[]{7,6,4,3,1}));
    }
}
