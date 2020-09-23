package OfferDemo;


/**
 * @Auther: wangzhendong
 * @Date: 2019/12/20 10:55
 * @Description:
 * 1在一个二维数组中（每个一维数组的长度相同），
 */
public class Test1 {

    //左下角开始找 : 时间复杂度:O(row+line) 空间复杂度:O(1)
    public static boolean leftDownFind(int target, int[][] array) {
        int row = array.length; //行
        int line = array[0].length; //列
        int i = row - 1, j = 0;
        while (i >= 0 && j < line) {
            if (target == array[i][j]) {
                return true;
            } else if (target > array[i][j]) {
                j++;
            } else {
                i--;
            }
        }
        return false;
    }

    //从右上角查: 同左下角查
    public static boolean rightUpFind(int target, int[][] array) {
        int row = array.length;
        int line = array[0].length;
        int i = 0, j = line - 1;
        while (i < row && j >= 0) {
            if (target == array[i][j]) {
                return true;
            } else if (target > array[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    //暴力法：时间复杂度：O(n^2) 空间复杂度：O(1)
    public static boolean simpleFind(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean test(int target, int[][] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        int n = array.length;
        int m = array[0].length;
        int i = n - 1, j = 0;
        while (i >= 0 && j < m) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //第一种方式：
        int a[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int b[][] = {{1, 2, 8, 9}, {4, 7, 10, 13}};
        //7,[[1,2,8,9],[4,7,10,13]]
        System.out.println(leftDownFind(7, b));
    }

}
