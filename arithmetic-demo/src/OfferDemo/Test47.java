package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 11:44
 * @Description:
 * 47，求1+2+3+...+n
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Test47 {

    /**
     * 思路；
     * 想到用位运算代替乘法，但没想出来
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        return  1;
    }

    /**
     * 答案1：
     * 这是一个等差数列，sum=(a1+an)n/2=>(1+n)n/2=>(n+n^2)/2；Math.pow(a,b)表示a^b；右移一位相当于除以2。
     * @param n
     * @return
     */
    public int Sum_Solution1(int n){
        int b = (int)Math.pow(n,2);
        return (n+b)>>1;
    }

    /**
     * 答案2：
     * 短路思想，当n=0时，sum=0，&&后面的就不会执行了，直接返回sum=0
     * 逻辑或的短路特性：当||左部分的表达式为true，则不执行右部分的表达式
     * @param n
     * @return
     */
    public int Sum_Solution2(int n){
        int sum=n;
        //n=1时直接返回1
        boolean flag=(sum>0)&&((sum+=Sum_Solution(n-1))>0);
        return sum;
    }

    public static void main(String[] args) {
        Test47 t = new Test47();
        System.out.println(t.Sum_Solution2(1));
    }
}
