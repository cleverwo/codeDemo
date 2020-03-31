import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: 10413
 * @Date: 2020/3/24 22:11
 * @Description:
 * 阿里的 1题
 */
public class Alibaba {

    //大数计算
    public static int bigNumber(int n){
        int m = 1000000007;
        BigDecimal re = BigDecimal.valueOf(2).pow(n-1).multiply(BigDecimal.valueOf(n))
                .divideAndRemainder(BigDecimal.valueOf(m))[1];
        return re.intValue();
    }

    public static int bigNumber2(int n){
        int m = 1000000007;
        // 2^n-1 * n
        int index = n-1;
        BigDecimal result = new BigDecimal(1);
        BigDecimal x = new BigDecimal(2);
        while (index!=0){
            if ((index&1)!=0){
                result = result.multiply(x)
                        .divideAndRemainder(new BigDecimal(m))[1];
            }
            x = x.multiply(x).divideAndRemainder(BigDecimal.valueOf(m))[1];
            index >>= 1;
        }
        BigDecimal re = result.multiply(BigDecimal.valueOf(n))
                .divideAndRemainder(BigDecimal.valueOf(m))[1];
        return re.intValue();
    }

    public static int bigNumber3(int n){
        int m = 1000000007;
        // 2^n-1 * n
        int index = n-1;
        int result = 1;
        int x = 2; // x的叠加会越界
        while (index!=0){
            if ((index&1)!=0){
                result = result*x%m;
            }
            x *= x;
            index >>= 1;
        }

        return x;
    }

    public static int quickPower(int n,int index,int mod){
        int result = 1;
        while (index != 0){
            if ((index&1)!=0){
                result *= n;
            }
            n *=n;
            if (n>1) return 1;
            index >>=1;
        }
        return result;
    }

    public static double Power3(double n,int e){
        // result用于存贮最终结果
        double result=1;
        //对e进行绝对值运算
        int b = e>0? e: -e;
        // b为指数,对指数进行位运算
        while (b!=0){
            // 取除b的低位是否为1，为1表示该位需进行幂运算，n^(2^1..)
            if ((b&1)!=0){
                // 该位存在幂运算进行数值相乘 例如：3^11 = 3^8 * 3^4 * 3^1
                // 这里即是 1 = 1* 3^1 ...
                result *=n;
            }
            // 低位a已经求出了,高位的幂是a^2,对其进行幂增
            n *=n;
            // 低位的幂运算进行进行完了,对指数b右移查看高位的是否存在幂运算
            b >>=1;
        }
        // result 即是 1*3^1 * 3^4 * 3^8（不存在3^2是因为 1101 中2的对应的是0）
        return e>0?result:1/result;
    }


    static long top = 1000000007;
    public static void main(String[] args) {
        int n = 1000000000;
        // 位运算解法
        //long t0 = new Date().getTime();
        //long ans2 = bitCal(n);
        long t1 = new Date().getTime();
        // 快速幂解法
        long ans1=n*fastPower(n-1) % top;
        long t2 = new Date().getTime();
        //System.out.println("位运算结果" + ans2);
        System.out.println("快速幂结果" + ans1);
        //System.out.println("快速幂结果" + bigNumber2(n));
        System.out.println("--------------");
       // System.out.println("位运算时间" + (t1 - t0));
        System.out.println("快速幂时间" + (t2 - t1));


    }

    // 快速幂解法
    static long fastPower(long n) {
        long ans=1, res=2;
        int i=2;
        while(n > 0){
            if((n&1) == 1) ans=ans*res%top;
            res=res*res%top;
            System.out.println("2的"+i+"次方 "+res);
            i *=2;
            n>>=1;
        }
        return ans%top;
    }

    // 位运算解法
    static long bitCal(long n) {
        long temp = n;
        long times = n - 1;
        while (times >= 33) {
            temp <<= 33;
            temp %= top;
            times -= 33;
        }
        temp <<= n;
        temp %= top;
        return temp;
    }
}
