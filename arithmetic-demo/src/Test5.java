import baseDemo.map.DFS;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/13 20:00
 * @Description:
 */
public class Test5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T>0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int k = sc.nextInt();
            Map<Integer, List<Integer>> map = new HashMap<>();
            for (int i=0;i<m;i++){
                int no1 = sc.nextInt();
                int no2 = sc.nextInt();
                int price = sc.nextInt();
                if (!map.containsKey(no1)){
                    List<Integer> list = new ArrayList<>();
                    map.put(no1,list);
                }
                if (!map.containsKey(no2)){
                    List<Integer> list = new ArrayList<>();
                    map.put(no2,list);
                }
                // 价格在 k 之内
                if (price<=k){
                    List<Integer> curr1 = map.get(no1);
                    List<Integer> curr2 = map.get(no2);
                    curr1.add(no2);
                    curr2.add(no1);
                    map.put(no1,curr1);
                    map.put(no2,curr2);
                }
            }
            // n 个岛， m条边，最少n-1条边 m>=n-1
            if (m<n-1){
                System.out.println("No");
                continue;
            }
            String res = judge(map,1,n);
            System.out.println(res);
            T--;
        }
    }

    private static String judge(Map<Integer, List<Integer>> map, int index,int n) {
        boolean[] visited = new boolean[n];
        dfs(map,visited,index);
        boolean res = true;
        for (int i =0;i<visited.length;i++){
            res = res && visited[i];
        }
        if (res){
            return "Yes";
        }else{
            return "No";
        }
    }

    public static void dfs(Map<Integer,List<Integer>> map, boolean[] visited,int index){
        if (visited[index-1]){
            return;
        }
        visited[index-1] = true;
        List<Integer> list = map.get(index);
        for (int i=0;i<list.size();i++){
            int tmp = list.get(i);
            dfs(map,visited,tmp);
        }
    }
}
