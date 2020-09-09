package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/28 11:47
 * @Description:
 * 8.跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class Test8 {
    public int JumpFloor(int target) {
        if(target <= 2){
            return target;
        }
        return JumpFloor(target-1)+JumpFloor(target-2);
    }

    //滚动数组记录状态，优化动规
    public int jumpFloorII2(int target){
        int p =1,q =2,sum = 1;
        for (int i=1;i<target;i++){
            sum = (p+q) % 1000000007;
            p = q;
            q = sum;
        }
        return p;
    }

    public static void main(String[] args) {
        Test8 t = new Test8();
        System.out.println(t.JumpFloor(4));
    }
}
