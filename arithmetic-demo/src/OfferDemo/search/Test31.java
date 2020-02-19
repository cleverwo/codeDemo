package OfferDemo.search;

import baseDemo.Test;

/**
 * @Auther: 10413
 * @Date: 2020/2/18 16:53
 * @Description:
 * 31，整数中1出现的次数（从1到n整数中1出现的次数）
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
 * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数
 * （从1 到 n 中1出现的次数）。
 */
public class Test31 {

    public int NumberOf1Between1AndN_Solution(int n) {
        return 0;
    }

    public int NumberOf1Between1AndN_Solution2(int n) {
        int count = 0;
        for (int i = n; i > 0; i--) {
            // 效率太低，对每个数计算有多少个1
            for (int j = i; j > 0; j /= 10) {
                if (j % 10 == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 答案：
     * 思路是分别计算个位、十位、百位........上出现 1 的个数。
     * 以  n =216为例：
     * 个位上： 1 ，11，21，31，.....211。个位上共出现（216/10）+ 1个 1 。
     * 因为除法取整，210~216间个位上的1取不到，所以我们加8进位。
     * 你可能说为什么不加9，n=211怎么办，这里把最后取到的个位数为1的单独考虑，先往下看。
     * 十位上：10~19，110~119，210~216.
     * 十位上可看成 求（216/10）=21 个位上的1的个数然后乘10。
     * 这里再次把最后取到的十位数为1的单独拿出来，
     * 即210~216要单独考虑 ，个数为（216%10）+1 .这里加8就避免了判断的过程。
     * 后面以此类推。
     * 时间复杂度 O(logN)
     *
     * 关键是先用数学归纳法总结
     */
    public int NumberOf1Between1AndN_Solution1(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            int c = (a+8)/10;
            int d = a%10 == 1?b+1:0;
            cnt += c * m + d;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Test31 t = new Test31();
        System.out.println(t.NumberOf1Between1AndN_Solution2(216));
    }

}
