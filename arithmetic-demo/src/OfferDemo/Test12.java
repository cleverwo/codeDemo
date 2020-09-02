package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/11 21:44
 * @Description:
 * 12.数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * 保证base和exponent不同时为0
 */
public class Test12 {

    /**
     * 暴力法 复杂度O(n)
     */
    public double Power(double base, int exponent) {
        if (exponent == 0){
            return 1;
        }
        double result = base;
        int z = exponent>0?exponent:-exponent;
        while(z!=1){
            result *=base;
            z--;
        }
        return exponent>0?result:(1/result);
    }

    /**
     * 公式法，调用内部函数
     */
    public static double Power2(double n,int e){
        return Math.pow(n,e);
    }

    /**
     * 经典算法快速幂算法 时间复杂度O(log2 N)
     *
     * 解题思路：
     * 例如：5^3 = 5^(2^1+2^0),将指数项写成二进制的表现形式
     */
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

    /**
     * 二分法 时间复杂度O(log2 N)
     * 思路： 假设e位正数
     * 则 e为偶数时 ： P(n,e) = P(n,e/2) * P(n,e/2)
     *   e为奇数时： P(n,e) = P(n,e/2) * P(n,e/2) * n
     * 时间复杂度是 O(logN)；由于采用递归结构，空间复杂度是 O(logN)。
     *
     * 主要的时抓只递归的结束值以p(3,11)为例推演，最总e/2到p（3，1） 后不能分了，所以e==1要有值返回
     */
    public static double Power4(double n,int e){
        boolean flag = e > 0;
        if (!flag){
            e = -e;
        }
        double result = getPower(n,e);
        return flag?result:1/result;
    }
    public static double getPower(double n,int e){
        //e==0不用算
        if (e==0){
            return 1;
        }
        //递归的结束位
        if (e==1){
            return n;
        }
        // 这里的主要写法也可为
        /**
         // 主要思想就是 根据e的奇偶性最总是否需要在乘以n
         double result =getPower(n,e/2);
         if (e%2!=0){
         result = result * result *n;
         }else{
         result = result*result;
         }
         return result;
         */
        // e>>1 相当于 e/2
        double result = getPower(n,e>>1);
        result *= result;
        // e&1 来判别e的奇偶性质，对于一个二进制数，只有最末位为1时为奇数，其他位都是2的倍数都是偶数，
        // 所以e&1 取的就是最末位的数 e&1==0 偶数 e&1==1 奇数
        if ((e&1)!=0){
            //奇数在乘其本身
            result *= n;
        }
        return result;
    }


    public static void main(String[] args) {
        Test12 t = new Test12();
        System.out.println(t.Power(2,-2));
        System.out.println(Power3(2,-2));
    }
}
