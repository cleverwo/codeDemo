package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 11:44
 * @Description:
 * 47，求1+2+3+...+n
 * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class Test47 {

    // 1+..+ n 数学问题，先计算公式 s = （1+n）*n/2，
    // sum=(a1+an)n/2=>(1+n)n/2=>(n+n^2)/2；Math.pow(a,b)表示a^b；右移一位相当于除以2。
    public int Sum_Solution(int n){
        int b = (int)Math.pow(n,2);
        return (n+b)>>1;
    }

    // 短路思想，当n=0时，sum=0，&&后面的就不会执行了，直接返回sum=0
    // 逻辑或的短路特性：当||左部分的表达式为true，则不执行右部分的表达式
    public int Sum_Solution2(int n){
        int sum=n;
        //n=1时直接返回1
        boolean flag=(sum>0)&&((sum+=Sum_Solution2(n-1))>0);
        return sum;
    }

    // 不适用+-*/的加法
    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }

    public static void main(String[] args) {
        Test47 t = new Test47();
        System.out.println(t.Sum_Solution2(1));
    }
}
