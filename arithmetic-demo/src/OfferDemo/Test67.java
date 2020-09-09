package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 15:57
 * @Description:
 * 67，减绳子
 * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 */
public class Test67 {

    /**
     * 思路：
     * 贪心算法，咋弄，
     * 这个数n=8 可以分成 1*7= 7 在把8分成俩数
     *
     */
    public int cutRope(int target) {
        if(target<2||target>60){
            return 0;
        }

        return 1;
    }


    /**
     * 答案1：
     * 递归求解，这是将t 分解成 1*t-1 在将t-1 分成了 1*t-2 依次类推
     */
    public int cutRope1(int target) {
        return cutRope1(target, 0);
    }
    public int cutRope1(int target, int max) {
        int maxValue = max;
        for(int i = 1; i < target; ++i){
            maxValue = Math.max(maxValue, i*cutRope1(target -i, target -i));
        }
        return maxValue;
    }

    /**
     * 答案2：
     * 动态规划
     */
    private static int cutRope2(int target) {
        int a = 0;
        int c = 0;
        int maxValue = 2;
        //输入参数范围验证
        if (2 > target || 60 < target) {
            return -1;
        }
        if (target == 2) {
            return 1;
        }
        if (target == 3) {
            return 2;
        }
        if (target % 3 == 0) {
            maxValue = (int)Math.pow(3, target / 3);
        } else{
            a = target - 2;
            c = a % 3;
            maxValue = maxValue * (int)Math.pow(3, a / 3);
            if (0 != c) {
                maxValue = maxValue * c;
            }
        }
        return maxValue;
    }

    /**
     * 首先推演：
     *  a1+a2+a3+……am=n
     *  a1*a2*a3*……am <= [(a1+a2+a3……am)/m]^m <= (n/m)^m
     *  则：f(m) = (n/m)^m = e^ [m*(ln n - ln m)] 变成求fm的最大值
     *  fm 的导数 = 0 求得 极点 m= n/e
     *  所以答案取 n/3 取最大值， 即n 尽可能的分出 n/e段
     */
    public int cutRope3(int n){
        if (n == 2){
            return 1;
        }
        //  n分成多少段， 最大分成m段
        int m =(int)(n* 1.0/2.7);
        // 每段的长度为x 均分
        int x = n/m;
        int y = n/(m+1);// n取n/e，这里假设分成m+1段 每段y均分
        int ansx = (int) (Math.pow(x,m-1) * (n - Math.pow(x,m-1)));
        int ansy = (int) (Math.pow(y,m) * (n- Math.pow(y,m)));
        int z = 0, ansz =0;
        if (m>2){
            z = n/ (m-2);
            ansz = (int) (Math.pow(z,m-2)*(n -Math.pow(z,m-2)));
            return Math.max(ansx,Math.max(ansy,ansz));
        }
        return Math.max(ansx,ansy);
    }

    public static void main(String[] args) {
        Test67 t = new Test67();
        System.out.println(t.cutRope2(12));
    }
}
