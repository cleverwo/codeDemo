package LeetCodeDemo.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/3/13 17:16
 * @Description: 5, 最长回文子串
 */
public class Solution5 {
    /**
     * 回文子串：
     * cbbc 中cb 对应bc 所以回文是cbbc
     * 最简单我遍历子串，入栈出栈
     */
    public String longestPalindrome(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));
            int j = i + 1;
            while (j < s.length()) {
                if (j != stack.peek()) {
                    stack.push(s.charAt(j));
                } else {
                    stack.pop();
                }
            }
            if (stack.isEmpty()) {

            }
        }
        return "";
    }

    /**
     * 暴力解法
     */
    public String longestPalindrome1_0(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        List<String> all = new ArrayList<>();
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                all.add(sub);
                if (isPair(sub) && sub.length() > max.length()) {
                    max = sub;
                }
            }
        }
        System.out.println(all.toString());
        return max;
    }

    private boolean isPair(String s) {
        String rev = new StringBuilder(s).reverse().toString();
        if (s.equals(rev)) {
            return true;
        }
        return false;
    }

    /**
     * 答案2： 标记数组，动态规划
     *
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        //构建标记数组,沿主对角线依次向右上靠拢
        // i起始0，j=i，i++ eg: abbc
        int n = s.length();
        boolean[][] dp = new boolean[n+1][n + 1];
        String max = "";
        // i表示行，j表示列 ,这是从主对角自下向上遍历的
        for (int j = 0; j < n + 1; j++) {
            for (int i = j; i >= 0; i--) {
                //初始化第一第二主对角线
                if (i == j || i + 1 == j) {
                    dp[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j - 1) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && max.length() < j - i) {
                    max = s.substring(i, j);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(s.longestPalindrome2("a"));
    }
}
