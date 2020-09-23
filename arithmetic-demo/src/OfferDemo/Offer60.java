package OfferDemo;

import OfferDemo.test.Offer;

/**
 * @Auther: 10413
 * @Date: 2020/9/9 19:11
 * @Description:
 * 60. n个骰子的点数
 */
public class Offer60 {

    // 动规
    /*
        思路：
            拆分，n个骰子的点数变为 n-1 个骰子点数 加最后 一个骰子的点数
            已知一个骰子点数的概率，求2个的概率... 依次类推
            寻找 1个骰子的概率 和 2个骰子概率的关系
        已知： n个骰子的点数范围为 【n ，6n】 即有6n-n+1中可能 即 5n+1种可能
        递推公式： tem[x+y] = p[x] * pre[y]
             下一个数组的temp 的概率 为 p[x] * p[y] x+y就是他们对应的下标。
     */
    public double[] twoSum(int n) {
        // pre 为初始状态
        double pre[]={1/6d,1/6d,1/6d,1/6d,1/6d,1/6d};
        // n >=2 才有结果，n = 1 即为pre，这里循环求 pre 的状态
        for(int i=2;i<=n;i++){
            // 当前的结果新建为长度 为 5n+1 ， n为遍历 求n 先求 n-1
            double tmp[]=new double[5*i+1];
            // 取 pre的 所有可能的值 的概率
            for(int j=0;j<pre.length;j++)
                // 下对应下标，如 j+x 0+0=0 表示了 骰子1 + 骰子1， 1+3=4 表示 骰子2+骰子4 = 6对应 temp的下标为4
                for(int x=0;x<6;x++)
                    // tmp[x+y] = pre[x] * num[y]
                    // tem[j+x] 表n-1 的概率，pre[j] 为 当前一个筛子的数
                    // num[y] === 1/6; 这里的1/6 就是num[y]
                    tmp[j+x]+=pre[j]/6;
            pre=tmp;
        }
        return pre;
    }

    //--------------------------------------
    public static void main(String[] args) {
        Offer60 o = new Offer60();
        double[] result = o.twoSum(2);
    }
}
