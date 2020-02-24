package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 16:08
 * @Description:
 * 48，不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 */
public class Test48 {

    /**
     * 思路：
     * 知道是位运算但没有思路
     */
    public int Add(int num1,int num2) {
        return 1;
    }

    /**
     * 答案：
     * 异或计算 做为相加  &运算当作进位
     * 在计组中，半加器、全加器中：
     * 两个二进制的相加结果是用一个异或门实现的；
     * 两个二进制的进位结果是用一个与门来实现的。
     * 如 101 + 011 5+3
     */
    public int Add1(int num1, int num2) {
        int result, ans;
        do {
            // 相同为0，不同为1，相当于 num1 + num2 但没有计算进位
            result = num1 ^ num2;       // 每一位相加
            // 都为1则为1， 左移相当于*2 ，这相当于 num1 和num2 相加哪一位需要进位，进位后在把进的一位和result相加
            ans = (num1 & num2) << 1;   // 进位
            num1 = result;
            num2 = ans;
            //知道计算到，ans不在进位，即ans为0
        } while (ans != 0);
        return result;
    }
}
