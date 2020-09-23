import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/20 16:29
 * @Description:
 */
public class laohu {

    public static class Node {
        public int cost;
        public int profit;

        public Node(int c, int p) {
            this.cost = c;
            this.profit = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        int[] costs = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }
        int[] profits = new int[n];
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }
        if (w<=0||k<=0){
            System.out.println(0);
            return;
        }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(costs[i], profits[i]);
        }
        long result = getResult(n,k,w,nodes);
        System.out.println(result);
    }

    public static long getResult(int n,int k,int w,Node[] nodes){
        long res = w;
        PriorityQueue<Node> minQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
        PriorityQueue<Node> maxQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.profit - o1.profit;
            }
        });
        for (int i = 0; i < n; i++) {
            minQueue.offer(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            while (!minQueue.isEmpty() && minQueue.peek().cost <= res) {
                maxQueue.offer(minQueue.poll());
            }
            if (maxQueue.isEmpty()) {
                return res;
            }
            res += maxQueue.poll().profit;
        }
        return res;
    }
}
