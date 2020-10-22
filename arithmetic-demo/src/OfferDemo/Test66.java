package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 15:39
 * @Description:
 * 66，机器人的运动范围
 * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
 * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），
 * 因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 */
public class Test66 {
    /**
     * 答案：
     * 用dfs或bfs 来求解
     *
     * 用dfs
     */
    //对应 右，左，上，下
    private final int dx[] = {1, -1, 0, 0};
    private final int dy[] = {0, 0, 1, -1};
    //获取坐标x的数位之和
    public int sum(int x) {
        int ans = 0;
        while (x > 0) {
            ans += x % 10;
            x /= 10;
        }
        return ans;
    }
    //移动坐标
    public int move(int threshold, int rows, int cols, int x, int y, boolean[][] vis) {
        vis[x][y] = true;
        int ans = 0;
        for (int i = 0; i < 4; i++) { // 向四个方向走
            int X = x + dx[i];
            int Y = y + dy[i];
            // x，y 大于0，x，y不能到负坐标
            if (X >= 0 && Y >= 0 && X < rows && Y < cols && !vis[X][Y] && sum(X) + sum(Y) <= threshold) {
                ans += move(threshold, rows, cols, X, Y, vis) + 1;
            }
        }
        return ans;
    }
    //入参为 threadsold 为下标和的最大值，rows和cols为盒子的长和宽
    public int movingCount1(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        //标记坐标
        boolean[][] vis = new boolean[rows][cols];
        return move(threshold, rows, cols, 0, 0, vis) + 1;
    }
}
