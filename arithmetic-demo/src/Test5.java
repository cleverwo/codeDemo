import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 21:21
 * @Description:
 */
public class Test5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a1 = sc.nextLine();
        String str = sc.nextLine();
        sc.close();
        String[] strs = str.split(" ");
        int n = Integer.valueOf(a1);
        List<Integer> list = new ArrayList<>();
        for (String s: strs){
            list.add(Integer.parseInt(s));
        }
        Collections.sort(list);
        int mid = n/2;
        int mid0 = list.get(n/2-1);
        int mid1 = list.get(mid);
        for (int i=0;i<n;i++){
            int num = Integer.parseInt(strs[i]);
            if (num<=mid0){
                System.out.println(mid1);
            }else{
                System.out.println(mid0);
            }
        }

    }
}
