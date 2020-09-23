package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 12:00
 * @Description:
 * 33 丑数
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Test33 {

    /**
     * 答案1：
     * 丑数能够分解成2^x3^y5^z，所以只需要把得到的丑数不断地乘以2、3、5之后并放入他们应该放置的位置即可
     * 如何有序的放在合适的位置：
     * 而在2x，3y，5z中，如果x=y=z那么最小丑数一定是乘以2的，但关键是有可能存在x》y》z的情况，
     * 所以我们要维持三个指针来记录当前乘以2、乘以3、乘以5的最小值，然后当其被选为新的最小值后，
     * 要把相应的指针+1；因为这个指针会逐渐遍历整个数组，因此最终数组中的每一个值都会被乘以2、乘以3、乘以5，
     * 也就是实现了我们最开始的想法，只不过不是同时成乘以2、3、5，而是在需要的时候乘以2、3、5.
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution1(int index) {
        if(index <= 0){
            return 0;
        }
        //初始化三个指向三个潜在成为最小丑数的位置
        int p2=0,p3=0,p5=0;
        //存储丑数
        int[] result = new int[index];
        // 第一个丑数是1
        result[0] = 1;
        for(int i=1; i < index; i++){
            result[i] = Math.min(result[p2]*2, Math.min(result[p3]*3, result[p5]*5));
            //这里取得丑数是 各指针*2，*3，*5的最小值，如果相等指针加1
            //所以：1，（1*2_，1*3，1*5），（2*2，1*3_，1*5），（2*2_，2*3，1*5）
            //（3*2，2*3，1*5_）,（3*2_，2*3_，2*5）,（4*2_，3*3，2*5）,（5*2，3*3_，2*5）
            //（5*2_，4*3，2*5_）
            if(result[i] == result[p2]*2){
                //为了防止重复需要三个if都能够走到
                //当前丑数是当前指针*2的结果，所以指针++，用于下一个丑数的比较
                p2++;
            }
            if(result[i] == result[p3]*3){
                p3++;//为了防止重复需要三个if都能够走到
            }
            if(result[i] == result[p5]*5){
                p5++;//为了防止重复需要三个if都能够走到
            }
        }
        return result[index-1];
    }

    // 暴力
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0){
            return 0;
        }
        int count = 0;
        int result = 1;
        int n = 1;
        while (count!=index){
            if(isChou(n)){
                count++;
                result = n;
            }
            n++;
        }
        return result;
    }
    public boolean isChou(int n){
        if (n==1){
            return true;
        }
        if (n%2==0){
            int m = n/2;
            return isChou(m);
        }
        if (n%3==0){
            int m = n/3;
            return isChou(m);
        }
        if (n%5==0){
            int m = n/5;
            return isChou(m);
        }
        return false;
    }

    public static void main(String[] args) {
        Test33 t = new Test33();
        System.out.println(t.GetUglyNumber_Solution1(11));
    }
}
