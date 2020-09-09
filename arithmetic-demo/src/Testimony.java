import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/6 20:02
 * @Description:
 */
public class Testimony {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // n 个人
        int m = sc.nextInt(); // m 团队
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<List<Integer>> together = new ArrayList<>();
        int start = -1;
        for (int i=0;i<m;i++){
            int mNum = sc.nextInt();
            List<Integer> currList = new ArrayList<>();
            for (int j=0;j<mNum;j++){
                int temp = sc.nextInt();
                if (temp == 0){
                    start = i;
                }
                if (!map.containsKey(temp)){
                    map.put(temp,new ArrayList<>());
                }
                currList.add(temp);
                List<Integer> list = map.get(temp);
                list.add(i);
                map.put(temp,list);
            }
            together.add(currList);
        }

        int[] mark = new int[m];
        Set<Integer> set = new HashSet<>();
        if (start == -1){
            System.out.println(0);
            return;
        }
        getResult(together,map,mark,set,start);
        System.out.println(set.size());
    }

    private static void getResult(List<List<Integer>> list,Map<Integer,List<Integer>> map,
        int[] mark, Set<Integer> set, int index) {
        if (mark[index] == 1){
            return;
        }
        set.addAll(list.get(index));
        mark[index] = 1;
        for (int num : list.get(index)){
            for (int gNo: map.get(num)){
                getResult(list,map,mark,set,gNo);
            }
        }
    }

}
