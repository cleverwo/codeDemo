import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/9/20 19:19
 * @Description:
 */
public class Test11 {
    static int p = 1000000007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.next();
        System.out.println(fast_pow(2,1000000000));
    }

    public static int fast_pow(int base,int index){
        int ans = 1;
        while(index>0){
            if(index%2==1){
                ans*=base;
            }
            index/=2;
            base*=base%p;
        }
        return ans;
    }
    public int[] printNumbers(int n) {
        int sz = fast_pow(10,n)-1;
        int[] ans = new int[sz];
        for(int i=0;i<sz;++i){
            ans[i] = i+1;
        }
        return ans;
    }

}
