package OfferDemo;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.lang.reflect.Array;
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/2/15 09:45
 * @Description: 19, 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Test19 {

    /**
     * 顺时针打印
     * 错误，，：输入 [[1],[2],[3],[4],[5]]
     * 应输出：[1,2,3,4,5]
     * 错误：数组越界
     *
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        //结尾
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        int i = 0, j = 0;
        int end = matrix.length, start = 0;
        //当i和j等于中间值跳出循环
        while (true) {
            // 左-右
            while (j < end) {
                list.add(matrix[i][j]);
                j++;
            }
            i++;
            j--;
            while (i < end) {
                list.add(matrix[i][j]);
                i++;
            }
            j--;
            i--;
            while (j >= start) {
                list.add(matrix[i][j]);
                j--;
            }
            i--;
            j++;
            while (i > start) {
                list.add(matrix[i][j]);
                i--;
            }
            i++;
            j++;
            start++;
            end--;
            if (i == end && j == start) {
                break;
            }
        }
        return list;
    }

    /**
     * 答案1： 判别方式思路和我的一样，
     * 不一样的是数组的结束为是否越界
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix2(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return list;
        }
        // 获取边界
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        //遍历
        while (true) {
            // 最上面一行 从左到右
            for (int col = left; col <= right; col++) {
                list.add(matrix[up][col]);
            }
            // 向下逼近 第一行遍历完了，上边界加1
            up++;
            // 判断是否越界 左右遍历判断上下，越界证明全遍历完了
            if (up > down) {
                break;
            }
            // 最右边一行
            for (int row = up; row <= down; row++) {
                list.add(matrix[row][right]);
            }
            // 向左逼近
            right--;
            // 判断是否越界
            if (left > right) {
                break;
            }
            // 最下面一行
            for (int col = right; col >= left; col--) {
                list.add(matrix[down][col]);
            }
            // 向上逼近
            down--;
            // 判断是否越界
            if (up > down) {
                break;
            }
            // 最左边一行
            for (int row = down; row >= up; row--) {
                list.add(matrix[row][left]);
            }
            // 向右逼近
            left++;
            // 判断是否越界
            if (left > right) {
                break;
            }
        }
        return list;
    }

    /**
     * 模拟答案1，边界判断
     * @param matrix
     * @return
     */
    public static ArrayList<Integer> printMatrix2_2(int[][] matrix){
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix==null||matrix.length==0||matrix[0].length==0){
            return list;
        }
        //获取边界
        int up = 0;
        int down = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;
        while(true){
            //上边界 从左-》右
            for (int j=left;j <=right;j++){
                list.add(matrix[up][j]);
            }
            up++;
            if (up>down){
                break;
            }
            //右边界 从上-》下
            for(int i=up;i<=down;i++){
                list.add(matrix[i][right]);
            }
            right--;
            if (right<left){
                break;
            }
            //下边界 从右-》左
            for (int j=right;j>=left;j--){
                list.add(matrix[down][j]);
            }
            down--;
            if (down < up){
                break;
            }
            //左边界 从下-》上
            for (int i=down;i>=up;i--){
                list.add(matrix[i][left]);
            }
            left++;
            if (left>right){
                break;
            }
        }
        return list;
    }

    /**
     * dx 对应 向右，向下，向左，向上 中x轴的变化
     * dy 对应的....y轴的变化
     */
    private final int[] dx = {0, 1, 0, -1};
    private final int[] dy = {1, 0, -1, 0};

    /**
     * 答案2： 标记数组
     * 判断哪一步是否可以走，首先，它没越界；其次，它没被走过（vis标记数组，为false代表没走过）
     * @param array
     * @return
     */
    public ArrayList<Integer> printMatrix3(int[][] array){
        // 获取数组的长度n行长，m列长
        int n = array.length, m = array[0].length;
        // 创建标记数组
        boolean[][] vis = new boolean[n][m];
        ArrayList<Integer> list = new ArrayList<>();
        // x，y为数组开始走的起始坐标，dir表述数组开始走的动作是向右
        int x = 0, y = 0, dir = 0;
        // x 属于[0,n], y属于[0,m] 数组不能越界且元素未被访问
        while (x >= 0 && x < n && y >= 0 && y < m && !vis[x][y]) {
            // 访问数组元素，标记为真
            list.add(array[x][y]);
            vis[x][y] = true;
            // 试着继续向dir的方向走
            // x，y向dir方向移动后数组不能越界，且数组未访问
            while (x + dx[dir] >= 0 && x + dx[dir] < n && y + dy[dir] >= 0 && y + dy[dir] < m && !vis[x + dx[dir]][y + dy[dir]]) {
                // 保证不越界且能访问的前提下 访问数组元素
                x += dx[dir];
                y += dy[dir];
                list.add(array[x][y]);
                vis[x][y] = true;
            }
            // 走不动了换方向
            dir = (dir + 1) % 4;
            x += dx[dir];
            y += dy[dir];
        }
        return list;
    }


    public static void main(String[] args) {
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] b = {{1}};
        int[][] c = {{1},{2},{3},{4},{5}};
        ArrayList<Integer> list = printMatrix2(a);
        System.out.println(list.toString());
    }
}
