package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/31 10:08
 * @Description:
 * 9.变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 贪心算法题
 */
public class Test9 {
    /**
     * 和跳台阶一样的迭代法
     * 跳n级的方法=跳n-1级+跳n-2级+...+跳n级
     * f(n) = 2f(n-1)
     * @param target
     * @return
     */
    public int JumpFloorII(int target) {
        if(target <= 2 ){
            return target;
        }
        return 2*JumpFloorII(target-1);
    }

    public static void main(String[] args) {
        Test9 t = new Test9();
        System.out.println(t.JumpFloorII(4));
    }
}
