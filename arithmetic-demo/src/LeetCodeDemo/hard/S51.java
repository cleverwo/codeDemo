package LeetCodeDemo.hard;

import java.util.*;

/**
 * @Auther: 10413
 * @Date: 2020/9/3 18:33
 * @Description:
 * 51. N 皇后
 */
public class S51 {
    /*int k;
    public List<List<String>> solveNQueens(int n) {
        //
        char[][] board = new char[n][n];
        k = n-1;
        dfs(0,0,board);
    }
    public void dfs(int x,int y, char[][] board){
        if (x == k || y==k ){
            return;
        }else {
            board[x][y] = 'Q';
            for (int i=0;i<=k;i++){
                if ()
            }
        }

    }*/

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>(); // 列
        Set<Integer> diagonals1 = new HashSet<Integer>(); // 正对角
        Set<Integer> diagonals2 = new HashSet<Integer>(); // 斜对角
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens,
                          int n, int row, Set<Integer> columns,
                          Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

}
