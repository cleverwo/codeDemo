import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 17:40
 * @Description:
 */
public class FaceTest {
    //  华为1面 9.2 14：00
    public static void getArray(int[] a, int[] b){
        int n = a.length;
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            if (!map.containsKey(a[i])){
                map.put(a[i],1);
            }else{
                map.put(a[i],map.get(a[i])+1);
            }
        }
        int m = b.length;
        for (int i=0;i<m;i++){
            if (map.containsKey(b[i])&&map.get(b[i])!=0){
                list.add(b[i]);
                map.put(b[i],map.get(b[i])-1);
            }
        }
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(list.toString());
    }
}
