import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoublePredicate;

/**
 * @Auther: 10413
 * @Date: 2020/3/24 20:11
 * @Description:
 * 360 2题
 */
public class Main {

    public int dna(String a, String b){
        if (a==null||b==null){
            return -1;
        }
        char[] cara = a.toCharArray();
        char[] carb = b.toCharArray();
        int n =cara.length,m = carb.length;
        int A=0, T=0;
        for (int i = 0;i<n;i++){
            if (cara[i]!=carb[i]){
                if (cara[i]=='A'){
                    A++;
                }else{
                    T++;
                }
            }
        }
        return Math.abs(A-T)+Math.min(A,T);

    }

    public void test(){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int m = c.nextInt();
        double[][] dp = new double[n+1][m+1];
        for (int i=0;i<=n;i++){
            for (int j=0;j<=m;j++){
                if (i==0&&j==0){
                    dp[i][j] = 0;
                }else if(i==0){
                    dp[i][j] = 0;
                }else if(j==0){
                    dp[i][j]=1;
                }else{
                    dp[i][j] = 1.0*i/(i+j) +
                            1.0*j/(i+j) *
                                    (j-1)/(i+j-1) *
                                    ((j-2>=0?1.0*i/(i+j-2)*dp[i-1][j-2]:0) +
                                            (j-3>=0?1.0*(j-2)/(i+j-2)*dp[i][j-3]:0));
                }
            }
        }
        System.out.println(dp[n][m]);
    }
    public void test1(){
        Scanner c = new Scanner(System.in);
        String a = c.next();
        boolean flag = true;
        if (a.charAt(0) == '-'){
            flag = false;
        }
        String[] b = new String[9];
        for (int i =0;i<9;i++){
            b[i] = c.next();
        }
        int n = a.length();
        int s = 0;
        StringBuffer result = new StringBuffer();
        if (!flag){
            result.append("-");
            s=1;
        }
        for (int i=s;i<n;i++){
            char num = a.charAt(i);
            int aa = Integer.parseInt(String.valueOf(num));
            result.append(b[aa-1]);
        }
        System.out.println(result);
    }

    public void test2(){
        Scanner c = new Scanner(System.in);
        int r = c.nextInt();
        int[] row = new int[r];
        for (int i=0;i<r;i++){
            row[i] = c.nextInt();
        }
        int n = row.length;
        Arrays.sort(row);
        BigDecimal result = new BigDecimal(0);
        boolean add =true;
        for (int i =n-1;i>=0;i=i-2){
            BigDecimal r1 = new BigDecimal(row[i]);
            r1 = r1.multiply(r1);
            BigDecimal r2 = new BigDecimal(0);
            if (i>0){
                r2 = new BigDecimal(row[i-1]);
            }else{
                r2 = new BigDecimal(0);
            }
            r2 = r2.multiply(r2);
            BigDecimal now = r1.subtract(r2);
            result = result.add(now);
        }
        result = result.multiply(new BigDecimal(Math.PI));
        double out = result.setScale(5,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(out);
    }


    public static void main(String[] args) {

    }

    public static boolean isFin(List<Integer> a){
        for (int i=0;i<a.size();i++){
            if (i%a.get(i)!=0){
                return false;
            }
        }
        return true;
    }

    public void tst(){
        Scanner c = new Scanner(System.in);
        int n = c.nextInt();
        int[] num = new int[n];
        StringBuffer s = new StringBuffer();
        for (int i=0;i<n;i++){
            num[i]= c.nextInt();
        }
        int mod = 998244353;
        int result = 0;
        int length = num.length;
        int end = 1<<length;
        int mark = 0;
        List<Integer> list = new ArrayList<>();
        for (mark = 0;mark<end;mark++){
            boolean isNull = true;
            for (int i=0;i<length;i++){
                if ((1<<i & mark)!=0){
                    isNull = false;
                    list.add(num[i]);
                    if (isFin(list)){
                        return;
                    }
                    System.out.println(num[i]+ " ");;
                }
            }
        }
        System.out.println();
    }

    //阿里笔试第一题
    public void Alibaba1(){
        Scanner c = new Scanner(System.in);
        String s = c.next();
        String t = c.next();
        int n = s.length();
        List<Character> list = new ArrayList<>();
        int i =0,j=0;
        int sum = 0;
        while (i<n&&j<n){
            char sc = s.charAt(i);
            char tc = t.charAt(j);
            if (sc!=tc){
                i++;
                list.add(sc);
            }else{
                i++;
                j++;
            }
        }
        while (j<n){
            char tc = t.charAt(j);
            if (list.contains(tc)){
                sum++;
                j++;
            }else{
                System.out.println(-1);
                return;
            }
        }
        System.out.println(sum);
    }

}
