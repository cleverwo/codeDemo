package LeetCodeDemo.string;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/11 22:03
 * @Description:
 * 3.无重复字符的最长子串
 */
public class Solution3 {
    /**
     * 做错了
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        char[] str = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        int b = 0;
        int e = 0;
        int max = Integer.MIN_VALUE;
        // abcabcbb asodf  碰到  a  pwwp 就嗝屁了
        while(e<str.length){
            if (map.containsKey(str[e])){
                //这里参考答案2 对b进行取最大值
                b = Math.max(b,map.get(str[e]));
                int sum = e - b + 1;
                if (sum > max){
                    max = sum;
                }
            }
            map.put(str[e],e+1);
            e ++;
        }
        int sum = e-b+1;
        if (sum > max){
            max = sum;
        }
        return max;
    }


    /**
     * 答案：
     * 差一点就想到了，我用了hashmap来控制坐标，其实思想就是滑动窗口
     * 主要点就在那个移除那
     * 看答案
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if(s==null||s.length()==0){
            return 0;
        }
        Set<Character> set = new HashSet<>();
        int n = s.length();
        int max = 0,i = 0, j = 0;
        while (i<n&&j<n){
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                max = Math.max(max,j-i);
            }else{
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }
    //优化
    public int lengthOfLongestSubstring2(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        // ①，用map存字符来判断是否重复，c字符，i表示最近最新c出现的位置
        Map<Character,Integer> map = new HashMap<>();
        int n = s.length();
        int max = 0;
        //② i，j就是滑动窗口的边界
        for (int i=0,j=0;j<n;j++){
            char c = s.charAt(j);
            if (map.containsKey(c)){
                i = Math.max(map.get(c),i);
            }
            //如果当前map不穿在c，则表示没有重复，那i的坐标还是0，就用j-i+1表示子串的长度。
            //如果map中存在了c，则需要重新定位i的位置，在定位i之前需要记录上一个无重复子串的大小，max用来存最大的子串长度
            max = Math.max(max,j-i+1);
            //c是字符， j+1表示如果重复，i的位置，不重复这个值就没意义了。
            map.put(c,j+1);
        }
        return max;
    }


    public static void main(String[] args) {
        Solution3 s = new Solution3();
        //System.out.println(s.lengthOfLongestSubstring1("au"));
        //System.out.println(s.lengthOfLongestSubstring1("abcabcbb"));
        //System.out.println(s.lengthOfLongestSubstring2("pwwkew"));
        //System.out.println(s.lengthOfLongestSubstring1("dvdf"));
        //System.out.println(s.lengthOfLongestSubstring1(" "));
        System.out.println(s.lengthOfLongestSubstring("pwwp"));
    }
}
