package LeetCodeDemo.eassy;

/**
 * @Auther: 10413
 * @Date: 2020/8/18 00:01
 * @Description:
 */
public class Solution461 {

    public static int hammingDistance(int x, int y) {
        int result = x ^ y;
        int index = 0;
        while( result != 0){
            int a = result & 1;
            if(a != 0){
                index++;
            };
            result >>= 1;
        }
        return index;
    }

    public static void main(String[] args) {
        //System.out.println(hammingDistance(1,3));
        System.out.println(Integer.bitCount(4));
    }

    // answer
    public int hammingDistance1(int x,int y){
        return Integer.bitCount(x ^ y);
    }
}
