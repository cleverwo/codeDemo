package OfferDemo;


/**
 * @Auther: wangzhendong
 * @Date: 2019/12/20 10:55
 * @Description:
 *
 * 1在一个二维数组中（每个一维数组的长度相同），
 * 每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Test1 {

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

    //错误方法,先二分查找确定2行数据在进行顺序查找
    public static boolean find(int target, int[][] array) {
        //列用二分法查找，对对角线二分查找
        int low = 0, hight = array.length - 1, mid = 0, pre = 0;
        if (hight <= 0) {
            return false;
        }
        // 先比较对接线的数字  0 1 2 3 4 5 6 7 8 9 10
        while (low <= hight) {
            pre = mid;
            mid = (low + hight) / 2;
            if (array[mid][mid] == target) {
                return true;
            } else if (array[mid][mid] > target) {
                hight = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        //未获取到数组，则在mid与pre之间
        int start = mid, end = pre;
        if (mid > pre) {
            //mid 大，在mid之前pre之后
            start = pre;
            end = mid;
        } else {
            // 在pre之前，mid之后
            start = mid;
            end = pre;
        }
        int i = start, j = start + 1;
        while (i <= end && j <= end) {
            if (array[i][j] == target) {
                return true;
            }
            j++;
            if (j > hight) {
                j = 0;
                i++;
            }
        }
        return false;
    }


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

    public static void main(String[] args) {
        //第一种方式：
        int a[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int b[][] = {{1, 2, 8, 9}, {4, 7, 10, 13}};
        //7,[[1,2,8,9],[4,7,10,13]]
        System.out.println(leftDownFind(7, b));
    }

}
