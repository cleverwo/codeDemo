package OfferDemo.array;


/**
 * @Auther: 10413
 * @Date: 2020/2/24 10:15
 * @Description:
 * 51,构建乘积数组
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 */
public class Test51 {

    /**
     * 思路：
     * 直接遍历暴力求解
     * 题意理解错误了，
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if(A==null||A.length==0){
            return new int[0];
        }
        int[] b = new int[A.length];
        int sum = 1;
        for (int i = 0,j=1;i< A.length;i++,j++){
            sum *= A[i];
            if (j<A.length){
                b[j] = sum;
            }
        }
        b[0] = sum;
        return b;
    }

    /**
     * 答案：
     * b的构造是一个三角
     * b0:  1   a1  a2  a3  a4  ... an-2    an-1
     * b1:  a0  1   a2  a3  a4  ... an-2    an-1
     * b2:  a0  a1  1   a3  a4  ... an-2    an-1
     * b3:  a0  a1  a2  1   a4  ... an-2    an-1
     * b4:  a0  a1  a2  a3  1   ... an-2    an-1
     * b5:  a0  a1  a2  a3  a4  1   an-2    an-1
     * ...                          1       an-1
     * bn-1:a0  a1  a2  a3  a4  ... an-2    an-1
     *
     * B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
     */
    public int[] multiply1(int[] A) {
        //边界
        if(A==null||A.length<=1){
            return null;
        }
        int length=A.length;
        int[] B=new int[length];
        //计算下三角
        //初始化第一行
        B[0]=1;
        for(int i=1;i<length;i++){
            B[i]=B[i-1]*A[i-1];
        }
        //计算上三角
        //初始化最后一行
        int temp=1;
        for(int i=length-1;i>=0;i--){
            B[i]=temp*B[i];
            temp=A[i]*temp;
        }
        return B;
    }

    public static void main(String[] args) {
        Test51 t =new Test51();
        t.multiply(new int[]{1,2,3,4,5});
    }
}
