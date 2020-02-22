package OfferDemo.string;

import baseDemo.Test;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Auther: 10413
 * @Date: 2020/2/21 22:02
 * @Description:
 * 45.扑克牌顺子
 * 一副扑克牌有2个大王2个小王，随机从中抽5张牌，大小王可作为任意值，
 * 要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，红心A,黑桃3,小王,大王,方片5” 1 3 0 0 5
 * 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
 */
public class Test45 {

    /**
     * 思路：
     * 大小王输入用0表示了，
     * 判断数组是否可能为有序数组
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if (numbers==null||numbers.length!=5){
            return false;
        }
        Set<Integer> set = new TreeSet<>();
        int large = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;
        int num=0;
        for(int i=0;i<numbers.length;i++){
            if (numbers[i]>large){
                large=numbers[i];
            }
            if (numbers[i]<low&&numbers[i]!=0){
                low = numbers[i];
            }
            if (numbers[i]!=0){
                set.add(numbers[i]);
            }else{
                num++;
            }
        }
        // large -low 不用等于4也可以，小于5即有序的，因为有0的存在，一个0就把差值减1
        // 修改large-low ==4 为large -low <5
        if ((large-low)<5&&(num+set.size()==5)){
            return true;
        }
        return false;
    }

    /**
     * 答案：很简单，两个判断
     * 1. 不计算0，去重后长度是否为5
     * 2. 最大最小之间的差是否是4
     */
    public boolean isContinuous1(int [] n) {
        if (n.length < 5 || n.length > 5) {
            return false;
        }
        int num = 0;
        TreeSet<Integer> set = new TreeSet<> ();
        for (int i=0; i<n.length;i++) {
            if (n[i]==0) {
                num ++;
            } else {
                set.add(n[i]);
            }
        }
        if ((num + set.size()) != 5) {
            return false;
        }
        if ((set.last() - set.first()) < 5) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Test45 t = new Test45();
        System.out.println(t.isContinuous(new int[]{1,3,2,6,4}));
    }
}
