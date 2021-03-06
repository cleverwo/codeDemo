package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 12:59
 * @Description:
 * 65,矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。例如 a b c e s f c s a d e e
 * 矩阵中包含一条字符串"abccee"的路径，但是矩阵中不包含"abcb"路径，
 * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class Test65 {
    // S79
    // dfs
    public boolean exist(char[][] board, String word) {
        int h = board.length;
        int w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i=0;i<h;i++){
            for (int j=0;j<w;j++){
                boolean flag = check(board,visited,i,j,word,0);
                if (flag){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean check(char[][] board, boolean[][] visited, int i, int j, String word, int k) {
        if (board[i][j] != word.charAt(k)){
            return false;
        }else if(k == word.length()-1){
            return true;
        }
        visited[i][j] = true;
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        boolean result = false;
        for (int[] dir: directions){
            int newi = i + dir[0];
            int newj = j + dir[1];
            if (newi>=0 && newi< board.length && newj >=0 && newj < board[0].length){
                if (!visited[newi][newj]){
                    boolean flag = check(board,visited,newi,newj,word,k+1);
                    if (flag){
                        result = true;
                        break;
                    }
                }
            }
        }
        visited[i][j] = false;
        return result;
    }
}
