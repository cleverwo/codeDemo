package OfferDemo.huawei;

import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/4/14 15:34
 * @Description:
 *
 * 华为题库--购物单
 */
public class Hua_Gouwudan {
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        int[] f = new int[n+1]; // 这个干啥的
        Node[][] nodes = new Node[m][3]; //分组存node
        for(int i=0;i<m;i++){
            int v = c.nextInt();
            int p = c.nextInt();
            int q = c.nextInt();
            Node node = new Node(v,v*p);
            if (q==0){
                nodes[i][0] = node;
            }else{
                if (nodes[q-1][1]==null){
                    nodes[q-1][1]= node;
                }else{
                    nodes[q-1][2]=node;
                }
            }
        }

        for (int i=0;i<m;i++){
            for (int j=n;j>=0&&nodes[i][0]!=null;j--){
                //以下代码从分组中选择价值最大的。
                // 共五种情况：不选主件，选主件，选附件1和主件，选附件2和主件，选附件1和附件2和主件
                // 三种，只选主键，选主键和附件1， 选主键和附件1，2
                Node node = nodes[i][0];
                int max = f[j];
                if (j>=node.money&&max<f[j-node.money]+node.weight){
                    max = f[j-node.money] + node.weight;
                }
                int vt;
                if (nodes[i][1] != null) {
                    if (j >= (vt = node.money + nodes[i][1].money)
                            && max <  f[j - vt] + node.weight + nodes[i][1].weight) {
                        max = f[j - vt] + node.weight + nodes[i][1].weight;
                    }
                }
                if (nodes[i][2] != null) {
                    if (j >= (vt = node.money + nodes[i][1].money + nodes[i][2].money)
                            && max < f[j - vt] + node.weight + nodes[i][1].weight + nodes[i][2].weight) {
                        max = f[j - vt] + node.weight + nodes[i][1].weight + nodes[i][2].weight;
                    }
                }
                f[j] = max;
            }
        }
        System.out.println(f[n]);
    }

    public static class Node{
        public int money;
        public int weight;

        public Node(int v,int p){
            this.money = v;
            this.weight = p;
        }
    }
}
