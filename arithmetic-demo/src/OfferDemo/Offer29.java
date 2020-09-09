package OfferDemo;

import OfferDemo.test.Offer;
import com.sun.corba.se.spi.legacy.connection.LegacyServerSocketEndPointInfo;

import java.util.Arrays;

/**
 * @Auther: 10413
 * @Date: 2020/9/7 15:16
 * @Description:
 *
 * 简单，顺时针打印数组
 */
public class Offer29 {

    // 边界问题边界的处理
    /*
    * 定义数组的 上下左右边界 为rStart， rEnd； lStart-lEnd；
    * 先遍历 rStart-rEnd的数组，遍历完成后 让rStart ++ ，相当于删除第一行数据
    * 在遍历 lStart-lEnd，遍历完 lEnd -- ，相当于删除最有一列
    * 在按顺时针的顺序遍历即可，注意弹出条件 rStart < rEnd ; lStart < lEnd;
    * */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0){
            return new int[]{};
        }
        int r = matrix.length;
        int l = matrix[0].length;
        int[] res = new int[r*l];
        int i = 0;
        int rStart = 0,rEnd = r-1,lStart = 0,lEnd = l-1;
        while(true){
            int a1,a2;
            for (a2=lStart; a2 <= lEnd ; a2++){
                res[i++] = matrix[rStart][a2];
            }
            rStart ++;
            if (rStart>rEnd){
                break;
            }
            for (a1=rStart;a1<=rEnd;a1++){
                res[i++] = matrix[a1][lEnd];
            }
            lEnd--;
            if (lEnd<lStart){
                break;
            }
            for (a2=lEnd;a2>=lStart;a2--){
                res[i++] = matrix[rEnd][a2];
            }
            rEnd--;
            if (rEnd < rStart){
                break;
            }
            for (a1=rEnd;a1>=rStart;a1--){
               res[i++] = matrix[a1][lStart];
            }
            lStart++;
            if (lStart > lEnd){
                break;
            }
        }
        return res;
    }
    //-------------------------------------

    //--------------------------------------
    public static void main(String[] args) {
        Offer29 o = new Offer29();
        int[][] test = {{1,2,3},{4,5,6},{7,8,9}};
        int[] res = o.spiralOrder(test);
        System.out.println(Arrays.toString(res));
    }
}
