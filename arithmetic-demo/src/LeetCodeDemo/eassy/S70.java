package LeetCodeDemo.eassy;

/**
 * @Auther: 10413
 * @Date: 2020/8/26 11:38
 * @Description:
 */
public class S70 {

    public int climbStairs(int n) {
        if(n<=1){
            return 1;
        }else if(n == 2){
            return 2;
        }
        return climbStairs(n-1) +climbStairs(n-2);
    }
    public int climbStairs2(int n) {
        int p=0,q=0,r=1;
        for (int i=1;i<=n;++i){
            p = q;
            q = r;
            r = p+q;
        }
        return r;
    }

    public static void main(String[] args) {
        S70 s = new S70();
        System.out.println(s.climbStairs(3));
    }
}
