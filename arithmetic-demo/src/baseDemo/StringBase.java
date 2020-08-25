package baseDemo;

/**
 * @Auther: 10413
 * @Date: 2020/8/25 10:51
 * @Description: 字符串匹配算法：
 * KMP
 * BM
 */
public class StringBase {


    // KMP算法主体逻辑。str是主串，pattern是模式串
    public static int kmp(String str, String pattern) {
        //预处理，生成next数组
        int[] next = getNexts(pattern);
        int j = 0;
        //主循环，遍历主串字符
        for (int i = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != pattern.charAt(j)) {
                //遇到坏字符时，查询next数组并改变模式串的起点
                j = next[j];
            }
            if (str.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            if (j == pattern.length()) {
                //匹配成功，返回下标
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }

    // 生成Next数组
    private static int[] getNexts(String pattern) {
        int[] next = new int[pattern.length()];
        int j = 0;
        // i 为匹配的子串的下一个位置，j为最大匹配子串的下一个位置
        // i = 0，1时不会存在最大匹配子串，i=0不匹配，i=1 匹配第一字母，还是没有匹配公共子串，也就没有最大匹配公共子串一说了
        for (int i = 2; i < pattern.length(); i++) {
            while (j != 0 && pattern.charAt(j) != pattern.charAt(i - 1)) {
                //从next[i+1]的求解回溯到 next[j]
                j = next[j];
            }
            // 一般计算最长公共子串，用后面的和前面的字符比较
            if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "ATGTGAGCTGGTGTGTGCFAA";
        String pattern = "GTGTGCF";
        String a = "abcdef";
        int[] b = getNexts(a);
        //int index = kmp(str, pattern);
        //System.out.println("首次出现位置：" + index);
    }
}
