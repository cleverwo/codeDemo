package LeetCodeDemo;

/**
 * @Auther: 10413
 * @Date: 2020/3/20 22:29
 * @Description:
 * 202. 快乐数
 */
public class Solution202 {

    /**
     * 旦等于1，再怎么递归下去都是1. 不然就会循环下去，直到追上。
     */
    public boolean isHappy(int n) {
        int fast = n,slow = n;
        do{
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }while (fast!=slow);
        return slow == 1;
    }
    public int bitSquareSum(int n){
        int sum = 0;
        while(n>0){
            int bit = n%10;
            sum += bit*bit;
            n = n/10;
        }
        System.out.println(sum);
        return sum;
    }

    public static void main(String[] args) {
        Solution202 s = new Solution202();
        System.out.println(s.isHappy(78));
    }
}
