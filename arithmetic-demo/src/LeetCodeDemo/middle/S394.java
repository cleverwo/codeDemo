package LeetCodeDemo.middle;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Auther: 10413
 * @Date: 2020/9/5 17:11
 * @Description:
 * 394 字符串解码
 */
public class S394 {

    public String decodeString(String s) {
        Stack<StringBuffer> strStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();
        if(s.length() == 0){
            return "";
        }
        int n = s.length();
        StringBuffer str = new StringBuffer();
        StringBuffer numStr = new StringBuffer();
        for(int i=0;i<n;i++){
            char c = s.charAt(i);
            if (Character.isDigit(c)){
                numStr.append(c);
            }else if(c == '['){
                int current = Integer.parseInt(numStr.toString());
                numStack.push(current);
                strStack.push(str);
                numStr = new StringBuffer();
                str = new StringBuffer();
            }else if(Character.isLetter(c)) {
                str.append(c);
            }else if( c == ']'){
                int num = numStack.pop();
                String temp = str.toString();
                while (num>1){
                    str.append(temp);
                    num--;
                }
                str.insert(0,strStack.pop());
            }
        }
        return str.toString();
    }
    //-------------------------------------------------------------
    int ptr;
    public String decodeString2(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }

    //-------------------------------------------------------------
    public static void main(String[] args) {
        S394 s = new S394();
        //System.out.println(s.decodeString("3[a2[c]]"));
        System.out.println(s.decodeString2("3[a2[c]]"));
    }

}
