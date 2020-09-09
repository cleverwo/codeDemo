package LeetCodeDemo.middle;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/5 16:28
 * @Description: 406 根据身高重建队列
 */
public class S406 {
    // ---------------做错了
    public int[][] reconstructQueue(int[][] people) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            Node node = new Node(people[i][0], people[i][1],people[i][1]);
            list.add(node);
        }
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                // return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
                //return o1.h == o2.h ? o1.k = o2.k : o2.h - o1.h;
                if (o1.h >= o2.h){
                    if (o2.temp > 0){
                        o2.temp --;
                        return -1;
                    }else{
                        return 1;
                    }
                }else{
                    if (o1.temp > 0){
                        o1.temp --;
                        return 1;
                    }else{
                        return -1;
                    }
                }
            }
        });
        int[][] result = new int[people.length][2];
        for (int i=0;i<people.length;i++){
            result[i][0] = list.get(i).h;
            result[i][1] = list.get(i).k;
        }
        return result;
    }

    class Node {
        int h;
        int k;
        int temp;

        public Node(int h, int k,int temp) {
            this.h = h;
            this.k = k;
            this.temp =temp;
        }
    }

    //-----------------------------------------------------------------
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if the heights are equal, compare k-values
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> output = new LinkedList<>();
        for(int[] p : people){
            output.add(p[1], p);
        }

        int n = people.length;
        return output.toArray(new int[n][2]);
    }
    //----------------------------------------------------------------
    public static void main(String[] args) {
        S406 s = new S406();
        int[][] test = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        int[][] result = s.reconstructQueue(test);
        int[][] result2 = s.reconstructQueue2(test);
        for (int i=0;i<result.length;++i){
            System.out.print("["+result[i][0] + ","+ result[i][1] + "]");
        }
        System.out.println();
        for (int i=0;i<result.length;++i){
            System.out.print("["+result2[i][0] + ","+ result2[i][1] + "]");
        }
    }
}
