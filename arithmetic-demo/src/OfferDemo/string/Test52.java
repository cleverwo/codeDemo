package OfferDemo.string;

import com.sun.deploy.security.TrustRecorder;

import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/2/24 10:47
 * @Description:
 * 52,正则表达式
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Test52 {

    public boolean match(char[] str, char[] pattern) {
        if ((str==null||str.length==0)&&(pattern==null||pattern.length==0)){
            return true;
        }
        if (pattern==null||pattern.length==0){
            return false;
        }
        StringBuilder patterns = new StringBuilder();
        for (int i=0;i<pattern.length;i++){
            patterns.append(pattern[i]);
        }
        StringBuilder strs = new StringBuilder();
        for (int i=0;i<str.length;i++){
            strs.append(str[i]);
        }
        boolean result = Pattern.matches(String.valueOf(patterns),strs);
        return result;
    }

    /**
     * 答案：
     * 总体思路是模拟正则表达式，不是用正则表达式
     */
    public boolean matchStr(char[] str, int i, char[] pattern, int j) {

        // 边界
        if (i == str.length && j == pattern.length) { // 字符串和模式串都为空
            return true;
        } else if (j == pattern.length) { // 模式串为空
            return false;
        }

        boolean flag = false;
        boolean next = (j + 1 < pattern.length && pattern[j + 1] == '*'); // 模式串下一个字符是'*'
        if (next) {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) { // 要保证i<str.length，否则越界
                return matchStr(str, i, pattern, j + 2) || matchStr(str, i + 1, pattern, j);
            } else {
                return matchStr(str, i, pattern, j + 2);
            }
        } else {
            if (i < str.length && (pattern[j] == '.' || str[i] == pattern[j])) {
                return matchStr(str, i + 1, pattern, j + 1);
            } else {
                return false;
            }
        }
    }
    public boolean match1(char[] str, char[] pattern) {
        return matchStr(str, 0, pattern, 0);
    }
}
