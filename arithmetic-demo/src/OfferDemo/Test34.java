package OfferDemo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 12:43
 * @Description:
 * 34，第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class Test34 {

    // 出现1次， 第一个
    // hashMap
    // 遍历字符串对应hashmap
    public char firstUniqChar(String s) {
        if (s.length() == 0){
            return ' ';
        }
        Map<Character,Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for(int i=0;i<cs.length;i++){
            if (map.containsKey(cs[i])){
                map.put(cs[i],map.get(cs[i])+1);
            }else{
                map.put(cs[i],1);
            }
        }
        for (int i=0;i<s.length();i++){
            if (map.get(s.charAt(i)) == 1){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    // 有序hash
    public char firstUniqChar2(String s) {
        Map<Character,Integer> map = new LinkedHashMap<>();
        if (s.length()==0){
            return ' ';
        }
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        for (Map.Entry<Character,Integer> enery : map.entrySet()){
            if (enery.getValue() == 1){
                return enery.getKey();
            }
        }
        return ' ';
    }

}
