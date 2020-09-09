package LeetCodeDemo;

import java.util.HashMap;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/3/18 11:51
 * @Description: 20. 有效的括号
 */
public class Solution20 {

    /**
     * 字符串，栈的题目 ,简单，不用在做这个了
     * 还是看看答案的栈的写法，用了map比较巧妙
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        if (s == null || s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char n = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(n);
            } else {
                char c = stack.peek();
                if (c == '(' && n == ')' || c == '[' && n == ']' || c == '{' && n == '}') {
                    stack.pop();
                } else {
                    stack.push(n);
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * hashmap + 栈 加了个hashmap 更慢，，
     */
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != map.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Solution20 s = new Solution20();
        String ss = "()[]{}";
        System.out.println(s.isValid(ss));

    }
}
