import org.omg.CORBA.LongLongSeqHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: myleetcode
 * @description
 * @author: LZ
 * @create: 2020-09-20 19:51
 **/
public class Test22 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=Integer.valueOf(scanner.nextLine());
        List<BigDecimal> result=new ArrayList<>();
        BigDecimal c=new BigDecimal(1000000007);
        for(int i=0;i<n;i++)
        {
            String[] line=scanner.nextLine().split(" ");
            BigDecimal a=new BigDecimal(line[0]);
            BigDecimal b=new BigDecimal(line[1]);
            BigDecimal re = null;
            switch (line[2]){
                case "+":
                    re=a.add(b);
                    break;
                case "-":
                    re=a.subtract(b);
                    break;
                case "*":
                    re=a.multiply(b);
                    break;
                case "^":
                    double res = bigNumber1(a.intValue(),b.intValue());
                    System.out.println(res);
                    re=new BigDecimal(res);
                    System.out.println(re.doubleValue());
            }
            if (re!=null)
            {
                re=re.divideAndRemainder(c)[1];
            }
            result.add(re);

        }
        scanner.close();
        result.forEach(re-> System.out.println(re.intValue()));
    }

    public static double bigNumber1(long x,int n){
        int top = 1000000007;
        int index = n >0? n :-n;
        double result = 1;
        // 快速幂解法
        while (index>0){
            if ((index&1)!=0){
                result = result*x%top;
            }
            x = x*x%top;
            index >>= 1;
        }
        double re = (double)result%top;
        return n>0?re:1/re;
    }

}
/**
 * 4
 * 1000000000 1000000000 +
 * 1000000000 -100000000 -
 * 1000000000 -100000000 *
 * -100000000 1000000000 ^
 **/