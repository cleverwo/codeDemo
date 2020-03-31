import java.util.Scanner;

/**
 * @Auther: 10413
 * @Date: 2020/3/27 22:28
 * @Description:
 */
public abstract class Test2 {

    public static void main(String[] args) {
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int sum = Integer.MIN_VALUE;
        for (int i =1;i<=n;i++){
            for (int j=i;j<=n;j++){
                int lcm = getlcm(i,j);
                int gcm = getgcm(i,j);
                int now = lcm-gcm;
                if (now>sum){
                    sum = now;
                }
            }
        }
        System.out.println(sum);
    }
    //最小公倍数
    public static int getlcm(int a, int b){
        return a*b/getgcm(a,b);
    }
    //最大公约数
    public static int getgcm(int a,int b){
        int num=1;
        for (int i=1;i<=a&&i<=b;i++){
            if (a%i==0&&b%i==0){
                num = i;
            }
        }
        return num;
    }


}
