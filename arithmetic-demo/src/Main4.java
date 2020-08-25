import com.sun.org.apache.xpath.internal.operations.Bool;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_HK;

import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/8/23 15:25
 * @Description: 爱奇艺， n的阶乘0的个数
 */
public class Main4 {

    //Test1
    public int CountZero(int n) {
        if (n < 5) {
            return 0;
        }
        int b = n / 5;
        return b + CountZero(b);
    }

    private static class Move {
        int a;
        int b;

        public Move(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public boolean equals(Object o){
            Move m = (Move)o;
            return a == m.a && b == m.b;
        }

    }

    public static void test2(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Map<Integer, Map<Integer,Boolean>> path = new HashMap<>();
        byte[] bytes = str.getBytes();
        int tempX =0;
        int tempY = 0;
        Map<Integer, Boolean> init = new HashMap<>();
        init.put(0,true);
        path.put(0,init);
        for (int i=0;i<bytes.length;i++){
            if (bytes[i] =='N'){
                tempY ++;
            }else if(bytes[i] == 'S'){
                tempY --;
            }else if(bytes[i] == 'E'){
                tempX ++;
            }else{
                tempX --;
            }
            if (path.containsKey(tempX)){
                if (path.get(tempX).containsKey(tempY)){
                    System.out.println(true);
                    return;
                }else{
                    path.get(tempX).put(tempY,true);
                }
            }else{
                Map<Integer,Boolean> temp = new HashMap<>();
                temp.put(tempY,true);
                path.put(tempX,temp);
            }
        }
        System.out.println(false);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (judge(s)){
            System.out.println("True");
        }else {
            System.out.println("False");
        }

    }

    public static boolean judge(String s){
        if (s == null){
            return false;
        }
        if(s.length() == 0){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        for (int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if (!map.containsKey(c)){
                stack.push(c);
            }else{
                if (stack.isEmpty()){
                    return false;
                }
                char c2 = map.get(c);
                if (c2 != stack.pop()){
                    return false;
                }
            }

        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }
}
