import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/20 17:20
 * @Description:
 */
public class laohu2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        int[] num = new int[n];
        for (int i=0;i<n;i++){
            num[i] = sc.nextInt();
        }
        Arrays.sort(num);
        for (int i=0;i<n;i++){
            if (i%2 == 1 && i!=n-1){
                int curr = num[i];
                num[i] = num[i+1];
                num[i+1] = curr;
            }
        }
        for (int i=0;i<num.length;i++){
            System.out.print(num[i] + " ");
        }
        System.out.println(Arrays.toString(num));
    }
}
