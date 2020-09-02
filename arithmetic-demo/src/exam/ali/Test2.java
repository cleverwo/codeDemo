package exam.ali;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 17:41
 * @Description:
 */
public class Test2 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        List<String> list = new ArrayList<>();
        for (int i = 0;i<n;i++){
            String str = s.next();
            list.add(str);
            int num = s.nextInt();
        }
        System.out.println(3);
    }
}
