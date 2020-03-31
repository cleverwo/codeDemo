package LeetCodeDemo.string;


/**
 * @Auther: 10413
 * @Date: 2020/3/20 17:17
 * @Description:
 * 165. 比较版本号
 */
public class Solution165 {

    /**
     * 字符串的比较，最暴力的就是拆分字符串
     * 依次比较
     * 思路对了，但有个问题， 比较1.0和1 不通过，考虑怎么半
     */
    public int compareVersion(String version1, String version2) {
        String[] a = version1.split("\\.");
        String[] b = version2.split("\\.");
        int i =0,j=0;
        while (i<a.length||j<b.length){
            int na = i<a.length?Integer.parseInt(a[i]):0;
            int nb = j<b.length?Integer.parseInt(b[i]):0;
            if (na>nb){
                return 1;
            }else if (na<nb){
                return -1;
            }else{
                i++;j++;
            }
        }
        return 0;
    }

    /**
     * 思路一样，有一个小技巧，看while
     */
    public int compareVersion1(String version1, String version2){
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            //这个技巧经常用到，当一个已经遍历结束的话，我们将其赋值为 0
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int a = Integer.parseInt(num1);
            int b = Integer.parseInt(num2);
            if (a>b){
                return 1;
            }else if (a<b){
                return -1;
            }else{
                i++;j++;
            }
        }
        return 0;
    }

    /**
     * int 不安全，可能满了溢出，这里用字符串比较
     */
    public int compareVersion2(String version1, String version2) {
        String[] nums1 = version1.split("\\.");
        String[] nums2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < nums1.length || j < nums2.length) {
            String num1 = i < nums1.length ? nums1[i] : "0";
            String num2 = j < nums2.length ? nums2[j] : "0";
            int res = compare(num1, num2);
            if (res == 0) {
                i++;
                j++;
            } else {
                return res;
            }
        }
        return 0;
    }
    private int compare(String num1, String num2) {
        //将高位的 0 去掉
        num1 = removeFrontZero(num1);
        num2 = removeFrontZero(num2);
        //先根据长度进行判断
        if (num1.length() > num2.length()) {
            return 1;
        } else if (num1.length() < num2.length()) {
            return -1;
        } else {
            //长度相等的时候
            for (int i = 0; i < num1.length(); i++) {
                if (num1.charAt(i) - num2.charAt(i) > 0) {
                    return 1;
                } else if (num1.charAt(i) - num2.charAt(i) < 0) {
                    return -1;
                }
            }
            return 0;
        }
    }

    private String removeFrontZero(String num) {
        int start = 0;
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                start++;
            } else {
                break;
            }
        }
        return num.substring(start);
    }

    public static void main(String[] args) {
        Solution165 s = new Solution165();
        System.out.println(s.compareVersion1("01","1"));

        //char 能加减比较吗
        char a = '1';
        char b= '1';
        System.out.println(a-b);
    }
}
