import java.lang.annotation.Target;
import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/3 17:13
 * @Description:
 */
public class Maintain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n>0){
            int niu = sc.nextInt();
            int[] nius = new int[niu];
            int te = sc.nextInt();
            List<int[][]> list = new ArrayList<>();
            for (int i=0;i<te;i++){
                int teNum = sc.nextInt();
                int[][] tes = new int[teNum][2];
                for(int j=0;j<teNum;j++){
                    int[] temp = new int[2];
                    temp[0] = sc.nextInt();
                    temp[1] = sc.nextInt();
                    tes[j] = temp;
                }
                list.add(tes);
            }
            int lo = 0,hi = list.size()-1;
            while (lo<hi){

            }
            for(int i=0;i<list.size();i++){
                int a = i,b=i+1;
                if (b<list.size()){
                    int[][] result = interval(list.get(i),list.get(i+1));
                    list.remove(a);
                    list.remove(b);
                    list.add(result);
                }
            }
            int[][] ans = list.get(0);
            System.out.println(list.toString());
        }
    }

    public static int[][] interval(int[][] a, int[][] b){
        List<int[]> ans = new ArrayList<>();
        int i=0,j=0;

        while (i<a.length && j<b.length){
            int lo = Math.max(a[i][0],b[j][0]);
            int hi = Math.max(a[i][1],b[j][1]);
            if (lo <= hi){
                ans.add(new int[]{lo,hi});
            }
            if (a[i][1] < b[j][1]){
                i++;
            }else {
                j++;
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    /**
      1
      10 2
      3
      1 2
      4 5
      8 8
      2
      1 4
      6 8
     */
}
