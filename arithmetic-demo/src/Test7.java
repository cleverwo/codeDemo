import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @Auther: 10413
 * @Date: 2020/9/20 10:11
 * @Description:
 */
public class Test7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = sc.next();
        String num2 = sc.next();
        //
        int start  = Integer.parseInt(num1);
        int end = Integer.parseInt(num2);
        int ans = 0;
        while (start<=end){
            if (judgeNumber(num1)){
                ans++;
            }
            start ++;
            num1 = String.valueOf(start);
        }
        System.out.println(ans);

    }

    public static boolean judgeNumber(String str){
        // 六位不相同
        char[] arr = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for (int i=0;i<arr.length;i++){
            set.add(arr[i]);
        }
        if (set.size()!=6){
            return false;
        }
        // ab + cd = ef
        try{
            int ab = Integer.parseInt(str.substring(0,2));
            int cd = Integer.parseInt(str.substring(2,4));
            int ef = Integer.parseInt(str.substring(4,6));
            if (ab+cd != ef){
                return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
