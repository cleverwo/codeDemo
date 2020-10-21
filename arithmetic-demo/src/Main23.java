import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/28 11:17
 * @Description:
 */
public class Main23 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String str = sc.next();

        String str = "abacdeff ";
        String a = getSub(str.trim());
        System.out.println(a);
    }

    public static String getSub(String s){
        Map<Character,Integer> map = new HashMap<>();
        int n = s.length();
        int j = 0;
        int i=0;
        int max = 0;
        int start = 0,end= 0;
        for (i=0;i<n;i++){
            char c = s.charAt(i);
            if (map.containsKey(c)){
                j = Math.max(map.get(c),j);
            }
            max = Math.max(max,i-j+1);
            if (i-j+1>=max){
                start = j;
                end = i;
            }
            map.put(c,i+1);
        }
        System.out.println(max);
        return s.substring(start,end+1);
    }
}
