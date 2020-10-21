package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/31 12:07
 * @Description:
 * 11.二进制中的1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 */
public class Test11 {

    /**
     * 分析：
     * 输入10进制数转2进制查看其中1的个数
     * 暴力法：十进制直接转2进制，2进制转字符串，遍历字符串查看1的个数。
     */
    public static int NumberOf1(int n) {
        int count = 0;
        String binary = Integer.toBinaryString(n);
        char[] bytes = binary.toCharArray();
        for(char b: bytes){
            if (b == '1'){
                count++;
            }
        }
        return count;
    }

    /**
     * 本题正解是运用位运算，优先知道二进制转十进制的原理
     * 已知负数的二进制用负数的补码表示。
     * 一个int型数共32位，计算其1的个数，n的低位一定有1，除了0
     * 正数右移：保持为正数，相当于/2。
     * 负数右移：保持为负数，移位前是负数，移位后保持是负数，因此移位后最高位设为1。如果一直右移，最终会变成-1，即(-1)>>1是-1。
     * 正数左移：不保持为正数，相当于*2。（注意：1左移31时为负数最大值）
     * 负数左移：不保持为负数，在左移的过程中会有正有负的情况。所以切记负数左移不会特殊处理符号位。如果一直左移，最终会变成0。
     *
     * 解题思路： 对n&1，得到其最低位的数，判断是否为1，n在右移1位，最终n为0结束
     * 对于负数，n右移的最终结果是-1，
     */
    public static int NumberOf2(int n){
        int num = 0,flag = 1;
        while(flag != 0){
            int b = n&flag;
            if (b!=0){
                num++;
            }
            flag <<=1; //flag右移，取高位，知道越位成0 最大为2^31 即
            // 1000 0000 0000 0000 0000 0000 0000 0000 -2^31
        }
        return num;
    }
    public static int result(int n){
        int num =0,flag = 1;
        while (n != 0){
            if ((n&flag)!=0){
                num ++;
            }
            n >>>=1;
        }
        return num;
    }

    /**
     * 独特解：
     * 主要思路是：
     * 如果一个整数不为0，那么这个整数至少有一位是1。如果我们把这个整数减1，
     * 那么原来处在整数最右边的1就会变为0，原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。
     * 其余所有位将不会受到影响。
     * 如： 1011 减 1 为 1010 在将两个数做与运算
     * 如1100&1011=1000.也就是说，把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0.
     * 那么一个整数的二进制有多少个1，就可以进行多少次这样的操作
     * 每次操作就将最右边的数变成0
     * 对于负数，操作性是一样的
     */
    public static int NumberOf3(int n){
        int num = 0;
        while (n != 0) {
            num++;
            n &= (n - 1);
        }
        return num;
    }


    public static void main(String[] args) {
        System.out.println(NumberOf1(-11));
        System.out.println(Integer.toBinaryString(-11));
        System.out.println(NumberOf2(-11));
    }
}
