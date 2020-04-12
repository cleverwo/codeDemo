import com.sun.javafx.image.IntPixelGetter;

import javax.jnlp.IntegrationService;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/4/1 14:37
 * @Description:
 */
public class Test4 {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        String str = c.nextLine();
        String[] nums = str.split("\\s+");
        int[] a  = new int[20];
        for (int i=0;i<20;i++){
            a[i] = Integer.parseInt(nums[i]);
        }
        int[] b = new int[10];
        for (int i=0;i<a.length;i++){
            b[a[i]-1]++;
        }
        int max = Integer.MIN_VALUE;
        int index = 9;
        for (int i=b.length-1;i>=0;i--){
            if (max<=b[i]){
                max = b[i];
                index = i;
            }
        }
        System.out.println(index+1);
    }
}
