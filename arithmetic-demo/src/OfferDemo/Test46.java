package OfferDemo;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 11:18
 * @Description:
 * 46,孩子们的游戏
 * 每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
 * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
 * 首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。
 * 每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,
 * 并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,
 * 可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
 * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
 *
 * 如果没有小朋友，请返回-1
 */
public class Test46 {

    /**
     * 思路：
     * 循环链表的出队问题
     * 暴力解法，模拟出列动作
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if (n==0){
            return -1;
        }
        int count = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0;i<n;i++){
            list.add(i);
        }
        for (int i=0;list.size()!=1;i=(i+1)%list.size()){
            if (count == m-1){
                count = 0;
                list.remove(i);
                //list中remove一个元素了，则这里的i不用在+1
                i--;
            }else{
                count++;
            }
        }
        return list.get(0);
    }

    /**
     * 答案：
     * 第二种是分析每次被删除数字的规律并直接计算圆圈中最后剩下的数字。
     * //TODO这个答案有问题，编译不通过
     */
    public int LastRemaining_Solution1(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % n;
        }
        return ans;

    }

    public static void main(String[] args) {
        Test46 t = new Test46();
        System.out.println(t.LastRemaining_Solution1(6,6));
    }
}
