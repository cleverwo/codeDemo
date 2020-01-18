package OfferDemo;


/**
 * @Auther: wangzhendong
 * @Date: 2019/12/20 10:55
 * @Description:
        * 在一个二维数组中（每个一维数组的长度相同），
        * 每一行都按照从左到右递增的顺序排序，
        * 每一列都按照从上到下递增的顺序排序。
        * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
        */
public class Test1 {
    public static void main(String[] args) {
        //第一种方式：
        int a[][]={{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int b[][]={{1,2,8,9},{4,7,10,13}};
        //7,[[1,2,8,9],[4,7,10,13]]
        System.out.println(leftDownFind(7,b));
    }

    //错误方法,先二分查找确定2行数据在进行顺序查找
    public static boolean find(int target, int [][] array){
        //列用二分法查找，对对角线二分查找
        int low = 0,hight = array.length-1,mid=0,pre=0;
        if (hight <=0){
            return false;
        }
        // 先比较对接线的数字  0 1 2 3 4 5 6 7 8 9 10
        while(low <= hight){
            pre = mid;
            mid = (low + hight)/2;
            if (array[mid][mid] == target){
                return true;
            }else if(array[mid][mid] > target){
                hight = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        //未获取到数组，则在mid与pre之间
        int start = mid,end=pre;
        if(mid > pre){
            //mid 大，在mid之前pre之后
            start=pre;end=mid;
        }else{
            // 在pre之前，mid之后
            start=mid;end=pre;
        }
        int i=start,j=start+1;
        while (i <= end && j <= end) {
            if (array[i][j] == target){
                return true;
            }
            j++;
            if (j > hight){
                j=0;i++;
            }
        }
        return false;
    }

    //暴力法：时间复杂度：O(n^2) 空间复杂度：O(1)
    public static boolean simpleFind(int target,int[][] array){
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[i].length;j++){
                if (array[i][j] == target){
                    return true;
                }
            }
        }
        return false;
    }

    //左下角开始找 : 时间复杂度:O(row+line) 空间复杂度:O(1)
    public static boolean leftDownFind(int target, int[][] array){
        int row = array.length; //行
        int line = array[0].length; //列
        int i=row-1,j=0;
        while(i>=0&&j<line){
            if (target==array[i][j]){
                return true;
            }else if(target > array[i][j]){
                j++;
            }else{
                i--;
            }
        }
        return false;
    }

    //从右上角查: 同左下角查
    public static boolean rightUpFind(int target,int[][] array){
        int row = array.length; int line = array[0].length;
        int i=0,j=line - 1;
        while (i<row&&j>=0){
            if (target == array[i][j]){
                return true;
            }else if (target > array[i][j]){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }

    /*
    *
二维折半查找法：
是我提交时使用的算法，没有法一简洁明了，但是也挺好用，复杂度最坏情况为O(N * logN)，此时需要查找的元素临近数组中心，不过总体性能优于方法三
二维数组分为上下左右四个边界top，bottom，left，right：
对上边界top进行折半查找，可以将二维数组位于终止点E右边的矩形Rr排除，因为终止点E小于其右边相邻的元素E+1，而E+1是右边矩形Rr的最小元素(左上元素)
同理，对下边界bottom折半，可以排除二维数组位于终止点E左边的矩形Rl排除，
对左边界left折半，可以排除二维数组位于终止点E下边的矩形Rb排除，
对右边界right折半，可以排除二维数组位于终止点E上边的矩形Rt排除，
一轮过去，边界范围缩小，对由新边界组成的矩形重复以上操作，直到范围缩小为只有一个元素

三、N行折半法：
也是很容易就能想到的方法，每一行都执行折半查找，持续N行，复杂度为O(N*logN)

四（一）、简单的十字分割法：
亲测可用，使用十字将数组等分为四个区域，中间交叉点用来判断，因为左上区域和右下区域必然冲突，所以二者必然可以排除一个，即每次至少可以排除1/4的数据，接着对剩下的三个区域进行递归操作，当然可以利用左上右下元素来判断是否落在区域内来进行优化，直到只有一个元素，递归层数为：logN，不优化的话最底层节点数为3^logN，优化后节点数会少很多，实测效率还算不错

四（二）、另一版本的十字分割法
与上一个类似，只不过这里先利用十字分割在主对角线方向上进行折半查找操作，操作结束后，终止点的左上区域与右下区域都可以排除，这样就只剩下左下、右上两个区域，然后再进行递归。感觉上效率会比上个版本好，不过这个我没有去实现验证*/

}
