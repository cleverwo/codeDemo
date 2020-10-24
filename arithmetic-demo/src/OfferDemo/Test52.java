package OfferDemo;

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

    // java正则API
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
        // java 正则匹配
        boolean result = Pattern.matches(String.valueOf(patterns),strs);
        return result;
    }

    // 动规
    /*
        匹配： s和p 的情况有三种：
        1. 正常字符 2. 为点.  3. 为星号*
        若从后匹配s和p 可分离出子问题。
        1. p的最后一个为字符， 则查看 s[n-1] == p[m-1] ? 匹配前n-2和m-2的字符结果 ： false
        2. p最后为. 则查看 匹配前n-2和m-2的字符结果
        3. p为* 则需要查看 p的前一个字符，p[m-3] 因为 p[m-3]* 是一个整体，判断0个或多个
            则  判断s[n-2] 以前的字符是否出现过 p[m-3]
             s[n-2] != p[m-3] ? * 可以表示0个，所以查看 n-2  与 m-4的匹配结果 ：
                这时 s[n-2] == p[m-3] 或 s[n-2] == '.' 则匹配完成，继续匹配 n-3 和 m-3 的匹配结果
        递推公式：
            f[i][j] 表示 s 前i 和 p 前j是否匹配
            对于1，2 可有： f[i][j] = f[i-1][j-1]
            对于3，则有p[][]* 分匹配和不匹配（可以为0）两种情况：
                不匹配： f[i][j] = f[i][j-2]
                匹配： f[i][j] = f[i-1][j]
         判定：
            空串匹配空串 true 即 f[0][0] = true
            空串匹配非空 计算，p为 a*b. 样子的就是false了
            非空比空 不匹配 全是false
            非空比非空 计算
         初始化：
            处理长度为0方便，设f[n+1][m+1] 下标表示第几个字符，f[0][0] 表示第0个字符，
            最终结果是f[n][m]，前n个，前m个。
     */
    public boolean isMatch(String s, String p) {
        // 边界
        if (s.length() == 0 && p.length() == 0){
            return true;
        }
        if (p.length() == 0){
            return false;
        }
        //初始化
        int n = s.length();
        int m = p.length();
        boolean[][] f = new boolean[n+1][m+1];
        f[0][0] = true;
        // i 和 j 表示第几个字符，不是下标 i-1 和 j-1 对应下标
        for (int i =0;i<=n;i++){
            for (int j=0;j<=m;j++){
                // 对应空正则 和 非空正则
                if (j == 0){
                    // 空正则， i为空才为true
                    f[i][j] = i==0;
                }else{
                    // 非空正则判断 j是否为*， 不是 * f[i][j] = f[i-1][j-1]
                    // j非0 j必定大于0，取p的第j位字符 是否是 *
                    if (p.charAt(j-1) != '*'){
                        // i == 0 表示 空串匹配非空的，p不为* 则必为false
                        // i> 0 对应 情况1 和 情况2
                        if (i>0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')){
                            f[i][j] = f[i-1][j-1];
                        }
                    }else{
                        // p 取得是 * ，对应情况3，看 还是 不看
                        // 不看
                        if(j>=2){
                            f[i][j] |= f[i][j-2];
                        }
                        // 看
                        if(i>=1 && j>=2 && (s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.')){
                            f[i][j] |= f[i-1][j];
                        }
                    }
                }
            }
        }
        return f[n][m];
    }

    public boolean isMatchs(String a, String b){
        return matchStr(a.toCharArray(),0,b.toCharArray(),0);
    }

    // 递归
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

}
