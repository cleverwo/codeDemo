import javax.sound.midi.Soundbank;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/20 10:41
 * @Description:
 */
public class Test8 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        String a = sc.next();
        String b = sc.next();
        if (a.length() !=n ||b.length() !=m ){
            System.out.println("No");
            return;
        }
        if (m == 0){
            System.out.println("Yes");
            System.out.println(0);
        }

        int i =0,j=0;
        int ans = 0;
        while (i<n && j<m){
            if (a.charAt(i) == b.charAt(j)){
                j++;
                ans += i+1;
            }
            i++;
        }
        if (j==m){
            System.out.println("Yes");
            System.out.println(ans);
        }else{
            System.out.println("No");
        }
    }
}
