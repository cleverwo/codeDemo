import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/29 20:01
 * @Description:
 */
public class Test3 {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        if (n<2||n>50){
            System.out.println(-1);
            return;
        }
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = c.nextLong();
        }
        int sum = 0;
        Arrays.sort(nums);
        long max = nums[n - 1];
        while(max>=n){
            max = max-n;
            for (int i=0;i<n-1;i++){
                nums[i] +=1;
            }
            nums[n-1] = max;
            Arrays.sort(nums);
            max = nums[n-1];
            sum++;
        }
        System.out.println(sum);
    }
}
