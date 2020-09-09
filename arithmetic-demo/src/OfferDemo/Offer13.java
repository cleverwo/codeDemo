package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 16:18
 * @Description:
 * 剑指 Offer 13. 机器人的运动范围
 */
public class Offer13 {
    // dfs求解，标记数组
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    int col;
    int row;
    int result = 0;
    public int movingCount(int m, int n, int k) {
        if(m<0||n<0||k<0){
            return 0;
        }
        boolean[][] visited = new boolean[m][n];
        col = n;
        row = m;
        move(k,0,0,visited);
        return result;
    }
    public void move(int k, int x,int y,boolean[][] visited){
        visited[x][y] = true;
        result++;
        for(int i=0;i<4;i++){
            int X = x + dx[i];
            int Y = y + dy[i];
            if(X >= 0 && X < row && Y >= 0 && Y < col
                    && !visited[X][Y] && sum(X)+ sum(Y) <=k){
                move(k,X,Y,visited);
            }
        }
    }
    public int sum(int x){
        int ans = 0;
        while(x>0){
            ans += x%10;
            x = x/10;
        }
        return ans;
    }
}
