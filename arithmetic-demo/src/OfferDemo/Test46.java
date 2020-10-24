package OfferDemo;

import java.util.ArrayList;

/**
 * @Auther: 10413
 * @Date: 2020/2/22 11:18
 * @Description:
 * 46,孩子们的游戏
 *  约瑟夫环
 *
 * 如果没有小朋友，请返回-1
 */
public class Test46 {

    // 模拟法
    public int lastRemaining2(int n, int m){
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
    /**
     * 思路：
     * 循环链表的出队问题
     * 暴力解法，模拟出列动作
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
     */
    public int LastRemaining_Solution1(int n, int m) {
        if(n == 1){
            return 0;
        }
        int ans = 0;
        for (int i=2;i<=n;i++){
            ans = (ans+m) %i;
        }
        return ans;
    }

    public static void main(String[] args) {
        Test46 t = new Test46();
        System.out.println(t.LastRemaining_Solution1(6,6));
    }
}
