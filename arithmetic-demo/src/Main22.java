import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/25 15:41
 * @Description:
 */
public class Main22 {
    static int res = 0;
    public static void main(String[] args) {
        Main22 m = new Main22();
    }

    public int leastWorkTime (int[] tasks, int n) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<tasks.length;i++){
            if (!map.containsKey(tasks[i])){
                map.put(tasks[i],1);
            }else{
                map.put(tasks[i],map.get(tasks[i])+1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            list.add(entry.getValue());
        }
        Collections.sort(list);
        int ans = getTime(list,n);
        return ans-1;
    }

    public static int getTime(List<Integer> list , int n){
        if (list.size() <=0){
            return 0;
        }
        int length = list.size();
        int min = list.get(0);
        List<Integer> ans = new ArrayList<>();
        if (length>n){
            res += length*min;
            for (int i=0;i<length;i++){
                int num = list.get(i)-min;
                if (num>0){
                    ans.add(num);
                }
            }
            getTime(ans,n);
        }else{
            res += (n+1)*min;
            for (int i=0;i<length;i++){
                int num = list.get(i)-min;
                if (num>0){
                    ans.add(num);
                }
            }
            getTime(ans,n);
        }
        return res;
    }
    static class Node{
        int index;
        int num;
        public Node(int i,int n){
            index = i;
            num = n;
        }
    }
    public int leastWorkTime1(int[] tasks, int n) {
        int time = 0;
        int[] nums = new int[10];
        for (int i=0;i<tasks.length;i++){
            nums[tasks[i]]++;
        }
        List<Node> list = new ArrayList<>();
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                list.add(new Node(i,nums[i]));
            }
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.num-o1.num;
            }
        });
        Queue<Node> queue = new LinkedList<>();
        for (int i=0;i<list.size();i++){
            queue.offer(list.get(i));
        }
        while (!queue.isEmpty()){
            int size = queue.size();
            if (size>n){
                while (size>0){
                    Node top = queue.poll();
                    top.num--;
                    time++;
                    size--;
                    if (top.num>0){
                        queue.offer(top);
                    }
                }
            }else{
                int other = n -size+1;
                while (size>0){
                    Node top = queue.poll();
                    top.num--;
                    time++;
                    size--;
                    if (top.num>0){
                        queue.offer(top);
                    }
                }
                if (queue.size() !=0){
                    time += other;
                }
            }
        }
        return time;
    }
}
