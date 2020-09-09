package OfferDemo;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 18:28
 * @Description:
 * 48. 最长不含重复字符的子字符串 中等
 */
public class Offer48 {
    // 滑动窗口
    public int lengthOfLongestSubstring(String s) {
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
}
