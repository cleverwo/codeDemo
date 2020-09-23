package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/2/16 15:57
 * @Description:
 * 67，减绳子
 */
public class Test67 {

    /**
     * 答案1：
     * 递归求解，这是将t 分解成 1*t-1 在将t-1 分成了 1*t-2 依次类推
     */
    public int cutRope(int target) {
        return dfs(target, 0);
    }
    public int dfs(int target, int max) {
        int maxValue = max;
        for(int i = 1; i < target; ++i){
            maxValue = Math.max(maxValue, i*dfs(target -i, target -i));
        }
        return maxValue;
    }

    /**
     * 答案2：
     * 数学公式 贪心
     */
    private static int cutRope2(int target) {
        int a = 0;
        int c = 0;
        int maxValue = 2;
        //输入参数范围验证
        if (2 > target || 60 < target) {
            return -1;
        }
        if (target <= 2) {
            return target-1;
        }
        if (target % 3 == 0) {
            maxValue = (int)Math.pow(3, target / 3);
        } else{
            a = target - 2;
            c = a % 3;
            maxValue = maxValue * (int)Math.pow(3, a / 3);
            if (0 != c) {
                maxValue = maxValue * c;
            }
        }
        return maxValue;
    }

    /**
     * 首先推演：
     *  a1+a2+a3+……am=n
     *  a1*a2*a3*……am <= [(a1+a2+a3……am)/m]^m <= (n/m)^m
     *  则：f(m) = (n/m)^m = e^ [m*(ln n - ln m)] 变成求fm的最大值
     *  fm 的导数 = 0 求得 极点 m= n/e
     *  所以答案取 n/3 取最大值， 即n 尽可能的分出 n/e段
     */
    public int cutRope3(int n){
        if (n <= 3){
            return n-1;
        }
        // 每段的长度为x 均分
        int x = n/3;
        int y = n%3;// n取n/e，这里假设分成m+1段 每段y均分
        if (y==0){
            return (int) Math.pow(3,x);
        }else if(y == 1){
            return (int)Math.pow(3,x-1)*4;
        }
        return (int) (Math.pow(3,x)*2);
    }

    public static void main(String[] args) {
        Test67 t = new Test67();
        System.out.println(t.cutRope2(12));
    }
}
