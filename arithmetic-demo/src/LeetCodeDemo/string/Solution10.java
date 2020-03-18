package LeetCodeDemo.string;

/**
 * @Auther: 10413
 * @Date: 2020/3/14 16:45
 * @Description:
 * 10. 正则表达式匹配//TODO 太难了放弃了
 */
public class Solution10 {

    /**
     * 根据. 和 * 把字符p补全
     */
    public boolean isMatch(String s, String p) {
        StringBuilder regex = new StringBuilder(p);
        return s.matches(p);
    }

    public static void main(String[] args) {
        Solution10 s = new Solution10();
        String a = "aab";
        String b = "c*a*b";
        System.out.println(s.isMatch(a,b));
    }
}
