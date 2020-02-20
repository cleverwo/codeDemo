package OfferDemo.string;

import java.util.HashMap;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 12:43
 * @Description: 34，第一个只出现一次的字符
 * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
 * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
 */
public class Test34 {

    /**
     * 思路：
     * 例如： 输入 sdfsfwecl 输出：d
     * 关键是只出现一次，且是第一个
     * 判断只出现一次肯定需要遍历字符串
     *
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() <= 0 || str.length() > 10000) {
            return -1;
        }
        int index = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        //从头到尾遍历，如果这个字符只出现一次
        for (int i = 0; i < str.length(); i++) {
            char n = str.charAt(i);
            if (map.containsKey(n)) {
                int num = map.get(n);
                map.put(n, ++num);
            } else {
                map.put(n, 1);
            }
        }
        //在遍历一遍看看 那个下标是1
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 答案1：
     * 和自己差不多，不同是我用的hashmap他用了一个字母转
     * ascii码的数组充当hashmap
     */
    public int FirstNotRepeatingChar1(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        // a-z 97-122 A-Z 65-90 所以 这里可以长度定为123
        int[] count = new int[256];
        //用一个类似hash的东西来存储字符出现的次数，很方便
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }
        //其实这个第二步应该也是ka我的地方，没有在第一时间想到只要在遍历一遍数组并访问hash记录就可以了
        for (int i = 0; i < str.length(); i++) {
            if (count[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 答案2：
     * 利用string 的方法
     */
    public int FirstNotRepeatingChar2(String str){
        for (int i = 0; i < str.length(); i++) {
            // 从头到尾遍历，字符第一次出现的索引和字符最后一次出现的索引相同时返回
            if (str.indexOf(str.charAt(i)) == str.lastIndexOf(str.charAt(i))){
                return i;
            }
        }
        return -1;
    }

}
