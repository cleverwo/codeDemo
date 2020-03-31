package LeetCodeDemo;

import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/21 21:55
 * @Description:
 * 696. 计数二进制子串
 */
public class Solution696 {
    public int countBinarySubstrings(String s) {
        String[] sub = new String[]{};
        for (int i=0;i<s.length();i++){
            for (int j=0;j<=s.length();j++){

            }
        }
        return 1;
    }

    public int countBinarySubstrings1(String s) {
        int[] groups = new int[s.length()];
        int t = 0;
        groups[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                groups[++t] = 1;
            } else {
                groups[t]++;
            }
        }

        int ans = 0;
        for (int i = 1; i <= t; i++) {
            ans += Math.min(groups[i-1], groups[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution696 s = new Solution696();
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        String a = c.next();
        System.out.println(s.countBinarySubstrings1(a));

    }
}
