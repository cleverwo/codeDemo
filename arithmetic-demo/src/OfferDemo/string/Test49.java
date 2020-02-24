package OfferDemo.string;

import baseDemo.Test;

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
    public int StrToInt(String str) {
        String match = "^[+\\-0-9][0-9]{1,}?";
        boolean isLegal = Pattern.matches(match,str);
        if (!isLegal){
            return 0;
        }
        //获取数字
        Pattern numbers = Pattern.compile("\\d+");
        Matcher matcher = numbers.matcher(str);
        int result = 0;
        if (matcher.find()){
            String num = matcher.group();
            result = Integer.parseInt(num);
        }
        //获取符号
        Pattern sign = Pattern.compile("^[+\\-]*");
        Matcher signMatcher = sign.matcher(str);
        int flag = 0;
        if (signMatcher.find()){
            String signs = signMatcher.group();
            for (int i=0;i<signs.length();i++){
                char c = signs.charAt(i);
                if (c=='-'){
                    flag++;
                }
            }
        }
        return flag%2==0?result:-result;
    }
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

    public static void main(String[] args) {
        Test49 t = new Test49();
        //int a = Integer.parseInt("+-5");
        //System.out.println(a);
        System.out.println(t.StrToInt("+-5"));
    }
}
