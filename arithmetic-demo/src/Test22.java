import org.omg.CORBA.LongLongSeqHelper;

import java.math.BigDecimal;
import java.util.*;

/**
 * @program: myleetcode
 * @description
 * @author: LZ
 * @create: 2020-09-20 19:51
 **/
public class Test22 {
    static Map<Integer,List<Integer>> preMap = new HashMap<>();
    static Map<Integer,List<Integer>> posMap = new HashMap<>();
    static int start = 1;
    static int end = 2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // renhu
        int m = sc.nextInt(); // bi
        int p = sc.nextInt(); // niuniu
        int[] mark1 = new int[n];
        int[] mark2 = new int[n];
        start  = 1;
        end = n;
        for (int j = 0;j<m;j++){
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            if (!preMap.containsKey(a1)){
                List<Integer> pres = new ArrayList<>();
                preMap.put(a1,pres);
            }else{
                List<Integer> list = preMap.get(a1);
                list.add(a2);
                preMap.put(a1,list);
            }
            if (!posMap.containsKey(a2)){
                List<Integer> pres = new ArrayList<>();
                preMap.put(a1,pres);
            }else{
                List<Integer> list = posMap.get(a2);
                list.add(a1);
                preMap.put(a2,list);
            }
        }
        dfs(p-1,mark1);
        dfs(p-1,mark2);
        for (int i=start;i<=(n-end);i++){
            System.out.print(i);
        }
    }


    //去尾
    public static void dfs(int s,int[] mark){
        List<Integer> pre = posMap.get(s);
        for (int i = 0;i < pre.size();i++){
            int n = pre.get(i);
            if (mark[n-1] != 1){
                end ++;
            }else{
                mark[n-1] = 1;
                dfs(n+1,mark);
            }
        }
    }

    public static void dfs2(int s,int[] mark){
        List<Integer> pre = preMap.get(s);
        for (int i = 0;i < pre.size();i++){
            int n = pre.get(i);
            if (mark[n-1] != 1){
                start ++;
            }
            mark[n-1] = 1;
            dfs2(n+1,mark);
        }
    }
}
