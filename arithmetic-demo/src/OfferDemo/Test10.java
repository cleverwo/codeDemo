package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/31 11:55
 * @Description:
 * 10.矩形覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class Test10 {

    /**
     * 分析:
     * 2*1的小矩形覆盖2*n的大矩形，因为一个边都是2，则n那条边的覆盖会有2中情况，用小矩形的1或者2的边
     * 实质上就成了跳台阶的问题。
     * n=1时，只有一种覆盖   f(1) = 1
     * n=2时，可以横着用2个1覆盖，可以竖着用一个2覆盖，f(2)=2
     * n=3时，先用1覆盖，则剩下的变成n=2的情况了 f(2)=2 所以有2种
     *        先用2覆盖，则剩下的变成n=1的情况了 f(1)=1 所以有1种
     *        所以 f(3) = f(2) + f(1) = 3种
     *  同理：
     *  n时   f(n)=f(n-1)+f(n-2)种
     *
     */
    public int RectCover(int target) {
        if(target <= 2){
            return target;
        }
        return RectCover(target-1)+RectCover(target-2);
    }
}
