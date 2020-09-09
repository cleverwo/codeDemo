import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/2 17:40
 * @Description:
 */
public class Interview {
    //  华为1面 9.2 16：00
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

    // 华为2面 9.3 17：00 快速排序
    public static int sort(int[] num, int low, int high) {
        int a = num[low];
        while (low < high) {
            while (low < high && num[high] >= a) {
                high--;
            }
            num[low] = num[high];
            while (low < high && num[low] <= a) {
                low++;
            }
            num[high] = num[low];
        }
        num[low] = a;
        return low;
    }
    public static void quickSort(int[] num, int low, int high) {
        if (low < high) {
            int mid = sort(num,low,high);
            quickSort(num, low, mid - 1);
            quickSort(num, mid + 1, high);
        }
    }



}
