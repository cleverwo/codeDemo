import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/13 20:27
 * @Description:
 */
public class Test6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<String> result = new ArrayList<>();
        int num = scanner.nextInt();
        for (int i = 0; i < num; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            for (int j = 0; j < m; j++) {
                int island1 = scanner.nextInt();
                int island2 = scanner.nextInt();
                int money = scanner.nextInt();
                if (money > k) continue;

                if (!map.containsKey(island1)){
                    List<Integer> list = new ArrayList<>();
                    map.put(island1,list);
                }
                if (!map.containsKey(island2)){
                    List<Integer> list = new ArrayList<>();
                    map.put(island2,list);
                }
                // 价格在 k 之内
                if (money<=k){
                    List<Integer> curr1 = map.get(island1);
                    List<Integer> curr2 = map.get(island2);
                    curr1.add(island2);
                    curr2.add(island1);
                    map.put(island1,curr1);
                    map.put(island2,curr2);
                }
            }

            String reStr;
            if (map.isEmpty()) {
                reStr = "No";
            } else {
                Set<Integer> set = new HashSet<>();
                int start = map.keySet().iterator().next();
                dfs(map, set, start);
                reStr = set.size() == n ? "Yes" : "No";
            }
            result.add(reStr);

            map.clear();
        }
        scanner.close();
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }

    private static void dfs(Map<Integer, List<Integer>> map, Set<Integer> set, int island) {
        if (set.contains(island)) return;
        set.add(island);
        for (int next : map.get(island)) dfs(map, set, next);
    }
}
