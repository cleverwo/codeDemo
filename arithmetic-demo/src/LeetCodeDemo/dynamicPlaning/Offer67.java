package LeetCodeDemo.dynamicPlaning;

import javax.swing.*;
import java.lang.annotation.Target;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 00:28
 * @Description:
 * 剑指offer 67 减绳子
 */
public class Offer67 {

    /**
     * 构建dp数组
     */
    int[] memo;
    int target;
    public int cutRope(int target) {
       if (target<=2){
           return 1;
       }
       if (target == 3){
           return 2;
       }
       memo = new int[target+1];
       memo[2]=1;
       memo[3]=2;
       memo[4]=4;
       int i = 5;
       while(i<=target){
           memo[i] = Math.max(2*memo[i-2],3* memo[i-3]);
           i++;
       }
       return memo[target];
    }

    public static void main(String[] args) {
        Offer67 o = new Offer67();
        System.out.println(o.cutRope(8));
    }

}
