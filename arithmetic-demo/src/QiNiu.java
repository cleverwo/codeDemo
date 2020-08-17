import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/4/28 19:47
 * @Description:
 * 七牛云笔试题
 */
public class QiNiu {
    private static int judgeDfsCount =0;
    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        int vertexNum = n;
        int[][] a = new int[n][n];
        for (int i=0;i<m;i++){
            int src = c.nextInt();
            int dest = c.nextInt();
            a[src-1][dest-1] = 1;
            a[dest-1][src-1] = 1;
        }
        boolean flag =false;
        if (n>=1&&m>0){
            flag = DFSGraph(a,vertexNum);
        }
        if (flag){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }


    public static void DFS(int[][] a,int[] visited,int v,int vertexNum){
        visited[v] = 1;
        judgeDfsCount++;
        for (int i=0;i<vertexNum;i++){
            if (a[v][i] !=0 && visited[i]==0){
                DFS(a,visited,i,vertexNum);
            }
        }
    }
    public static boolean DFSGraph(int[][] a,int vertexNum){
        boolean flag = false;
        int[] visited = new int[vertexNum];//顶点数
        DFS(a,visited,1,vertexNum);
        if (judgeDfsCount == vertexNum-1){
            flag = true;
        }
        return flag;
    }
}
