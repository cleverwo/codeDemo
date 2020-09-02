package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/28 09:28
 * @Description:
 * 7.斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Test7 {
    /**
     * 递归法 O(2^n) O(1)
     * F(n) = F(n-1)+F(n-2) n>=3 n∈ N*
     * F(n) = n n<=2
     *
     */
    public int Fibonacci(int n) {
        if(n<=1){
            return n;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
    }

    public int Fib1(int n){
        int[] dp = new int[40];
        dp[0] = 0;
        dp[1] = 1;
        for (int i=2;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }



    public static void main(String[] args) {
        Test7 t = new Test7();
        System.out.println(t.Fibonacci(39));
    }
}
