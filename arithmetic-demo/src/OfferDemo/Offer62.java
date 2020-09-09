package OfferDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/4 19:53
 * @Description:
 *
 * 简单题： 圆圈中最后剩下的数字
 */
public class Offer62 {

    // 数学问题
    public int lastRemaining(int n, int m) {
        if(n == 1){
            return 0;
        }
        int ans = 0;
        for (int i=2;i<=n;i++){
            ans = (ans+m) %i;
        }
       return ans;
    }

    //-----------------------------------
    // 模拟法
    public int lastRemaining2(int n, int m){
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
    //-----------------------------------
    public static void main(String[] args) {
        Offer62 offer = new Offer62();
        System.out.println(offer.lastRemaining(5,3));
    }
}
