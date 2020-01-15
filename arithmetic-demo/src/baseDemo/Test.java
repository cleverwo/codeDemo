package baseDemo;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/2 14:15
 * @Description:
 */
public class Test {

    public void test(){
        //第一种方式：
        int a[][]={{1,2,6},{4,5,6},{7,8,9}};
        //第二种方式；
        int[][] ints = new int[4][2];
        //ints[i][j] =__; //分别赋值
        //第三种方式：第二维的长度可以动态申请
        int[][] arr3 = new int[5][];//五行的长度
        for(int i=0; i<arr3.length; ++i){
            arr3[i]=new int[i+1];   //列的长度每次都变化。每次都要重新申请空间(长度)
            for(int j=0; j<arr3[i].length; ++j)
                arr3[i][j]= i+j;
        }
    }
}
