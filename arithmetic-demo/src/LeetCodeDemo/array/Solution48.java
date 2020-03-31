package LeetCodeDemo.array;

import java.rmi.MarshalException;

/**
 * @Auther: 10413
 * @Date: 2020/3/20 15:53
 * @Description:
 * 48. 旋转图像
 */
public class Solution48 {

    public void rotate(int[][] matrix) {
        //转置
        for (int i=0;i<matrix.length;i++){
            for (int j=i;j<matrix[i].length;j++){
                int swap = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = swap;
            }
        }
        //反转
        for(int i=0;i<matrix.length;i++){
            int low =0,high = matrix[i].length-1;
            while (low<high){
                int swap = matrix[i][low];
                matrix[i][low] = matrix[i][high];
                matrix[i][high] = swap;
                low++;high--;
            }
        }

    }
}
