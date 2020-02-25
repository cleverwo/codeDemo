package OfferDemo.string;


import OfferDemo.Test5;
import baseDemo.Test;
import com.sun.xml.internal.messaging.saaj.util.TeeInputStream;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/2/14 11:38
 * @Description: 54，字符流第一个不重复的字符
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * 如果当前字符流没有存在出现一次的字符，返回#字符。
 */
public class Test54 {
    /**
     * 思路：
     * 用队列存储字符，在出队判断
     * 错误：
     * "google"  -》"ggg#ll"
     * 我的输出："google"
     * 存字符流有问题
     */
    Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        queue.offer(ch);
    }

    /**
     * 第一个只出现一次的字符，和前面求字符串的第一个出现一次的字符很像，不同是这个是动态添加的
     * 需要保存第二次，，，只出现一次的字符
     */
    public char FirstAppearingOnce() {
        if (queue.isEmpty()) {
            return '#';
        }
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        char result = '#';
        while (!queue.isEmpty()) {
            char c = queue.poll();
            str.append(c);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        String string = str.toString();
        for (int i = 0; i < string.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                result = str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 上面是对string stream的处理有问题，不用队列存了，我用数组
     */
    ArrayList<Character> list = new ArrayList<>();
    public void Insert_0(char ch) {
        list.add(ch);
    }

    /**
     * 字符都存在list中
     */
    public char FirstAppearingOnce_0() {
        if (list.isEmpty()) {
            return '#';
        }
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder str = new StringBuilder();
        char result = '#';
        for (int i = 0; i < list.size(); i++) {
            char c = list.get(i);
            str.append(c);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        String string = str.toString();
        for (int i = 0; i < string.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                result = str.charAt(i);
                break;
            }
        }
        return result;
    }


    /**
     * 答案： 思路和我的第二次做法时一样的
     */
    String input = "";
    Map<Character, Integer> map = new HashMap<>();

    public void Insert1(char ch) {
        if (!map.keySet().contains(ch)) {
            map.put(ch, 1);
        } else {
            map.put(ch, map.get(ch) + 1);
        }
        input += ch;
    }

    public char FirstAppearingOnce1() {
        int index = Integer.MAX_VALUE;
        char result = '#';
        for (Character c : map.keySet()) {
            if (map.get(c) == 1) {
                // 获取字符c 的下标
                if (input.indexOf(c) < index) {
                    // 重置下标
                    index = input.indexOf(c);
                    result = input.charAt(index);
                }
            }
        }
        return result;
    }

    /**
     * 答案2：用队列存储只出现一次的字符
     */
    int[] charCnt = new int[128];
    Queue<Character> queue1 = new LinkedList<Character>();
    public void Insert2(char ch) {
        //新来的单身字符，入队 出现过的字符不会在入队了，只将charCnt对应字符测次数++
        if (charCnt[ch]++ == 0) {
            queue.add(ch);
        }
    }
    public char FirstAppearingOnce2() {
        Character CHAR = null;
        char c = 0;
        while ((CHAR = queue.peek()) != null) {
            c = CHAR.charValue();
            //判断是否脱单了，没脱单则输出
            if (charCnt[c] == 1) {
                return c;
            } else {
                //脱单了就移出队列，它不会再回来了
                queue.remove();
            }
        }
        return '#'; //队空，返回#
    }

    public static void main(String[] args) {
        Test54 t = new Test54();
        t.Insert2('g');
        System.out.println(t.FirstAppearingOnce2());
        t.Insert2('o');
        System.out.println(t.FirstAppearingOnce2());
        t.Insert2('o');
        System.out.println(t.FirstAppearingOnce2());
        t.Insert2('g');
        System.out.println(t.FirstAppearingOnce2());
        t.Insert2('g');
        System.out.println(t.FirstAppearingOnce2());
        t.Insert2('e');
        System.out.println(t.FirstAppearingOnce2());

    }
}
