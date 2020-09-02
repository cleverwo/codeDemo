package LeetCodeDemo.middle;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/1 11:48
 * @Description:
 * 49. 字母异位词分组
 */
public class S49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 ){
            return new ArrayList<>();
        }
        Map<String,List<String>> map = new HashMap<>();
        for (String s : strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!map.containsKey(key)){
                List<String> children = new ArrayList<>();
                children.add(s);
                map.put(key,children);
            }else{
                List<String> children = map.get(key);
                children.add(s);
            }
        }
        return new ArrayList<>(map.values());

    }
}
