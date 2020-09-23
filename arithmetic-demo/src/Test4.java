import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/9/13 19:37
 * @Description:
 */
public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ns = sc.nextLine();
        String str = sc.nextLine();
        sc.close();
        int n = Integer.parseInt(ns);
        StringBuilder res = new StringBuilder();
        int j = n;
        int i = 0;
        while (i < str.length()) {
            j = Math.min(j, str.length());
            String sub = str.substring(i, j);
            String curr = reverse(sub);
            res.append(curr);
            i = i + n;
            j = j + n;
        }
        System.out.println(res.toString());
    }

    public static String reverse(String str) {
        char[] cs = str.toCharArray();
        for (int l = 0, h = cs.length - 1; l < h; l++, h--) {
            char tmp = cs[l];
            cs[l] = cs[h];
            cs[h] = tmp;
        }
        return new String(cs);
    }
}
