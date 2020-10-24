package OfferDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 16:23
 * @Description:
 * 49，把字符串转成整数
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * 输入一个字符串,包括数字字母符号,可以为空
 * 如果是合法的数值表达则返回该数字，否则返回0
 * +2147483647 -> 2147483647
 * 1a33 -> 0
 */
public class Test49 {

    /**
     * 思路：
     * 可以考虑用正则表达式来判断字符串的合法性
     * 编译出错，没有考虑int的大小范围，在-2^31 ~ 2^31-1 之间
     * @param str
     * @return
     */
    //通过long来判断边界
    public int StrToInt1(String str) {
        String match = "^[+\\-0-9][0-9]{1,}?";
        boolean isLegal = Pattern.matches(match,str);
        if (!isLegal){
            return 0;
        }
        Long num = Long.parseLong(str);
        if (num>Integer.MAX_VALUE||num<Integer.MIN_VALUE){
            return 0;
        }
        int result = new Long(num).intValue();
        return result;
    }

    public int strToInt(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if(c[0] == '-') sign = -1;
        else if(c[0] != '+') i = 0;
        for(int j = i; j < c.length; j++) {
            if(c[j] < '0' || c[j] > '9') break;
            // res > bndry 拼接的res *10 》 2147483650 越界
            // res = bndry && c[j] > 7 表示int 越界了，放回最大值Max就行
            if(res > bndry || res == bndry && c[j] > '7') {
                // 可以return -1
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }

    public static void main(String[] args) {
        Test49 t = new Test49();
        //int a = Integer.parseInt("+-5");
        //System.out.println(a);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(t.strToInt("-2147483648"));
    }
}
