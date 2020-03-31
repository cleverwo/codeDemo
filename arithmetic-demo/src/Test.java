import javafx.scene.shape.VLineTo;

import java.lang.management.GarbageCollectorMXBean;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @Auther: 10413
 * @Date: 2020/3/22 19:09
 * @Description:
 * 快手前三题
 */
public class Test {

    public int[] test(int[] height){
        if (height==null||height.length==0){
            return new int[]{};
        }
        int n = height.length;
        int[] out = new int[n];
        for (int i=n-1;i>0;i--){
            int j = i-1;
            while (j>=0){
                if (height[i]>=height[j]){
                    j--;
                }else{
                    break;
                }
            }
            if (j<0){
                out[i] = 0;
            }else{
                out[i] = i-j;
            }
        }
        return out;
    }

    public String test1(){
        Scanner c = new Scanner(System.in);
        String input = c.nextLine();
        if (input==null||input.length()==0){
            return "-1";
        }
        String[] numbers = input.split("\\s+");
        int n = numbers.length;
        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = Integer.parseInt(numbers[i]);
        }
        /*int[] nums = {};
        int n =nums.length;
        if(n<=1){
            return "-1";
        }*/
        String result="";
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
            if (nums[i]>=max1){
                max2 = max1;
                max1 = nums[i];
            }else if (nums[i]>=max2){
                max2 = nums[i];
                result = result + i + " ";
            }
        }
        return result==""?"-1":result;
    }

    public void phoneNum(String[] nums){
        if (nums==null||nums.length==0){
            return;
        }
        int n = nums.length;
        HashMap<Integer,String> map = new HashMap<>();
        for (int i=0;i<n;i++){
            int result = getGood(nums[i]);
            if (result>0){
                map.put(result,nums[i]);
            }
        }
        map.forEach((key, value)->{
            if (key==4){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==3){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==2){
                System.out.print(value);
            }
        });
        map.forEach((key, value)->{
            if (key==1){
                System.out.print(value);
            }
        });
    }
    //判断优先级
    public int getGood(String number){
        Queue<Character> bao = new LinkedList<>();
        Queue<Character> shun = new LinkedList<>();
        int grade = 0;
        int grade2 = 0;
        String num = number.substring(3);
        char[] cn = num.toCharArray();
        shun.offer(cn[0]);
        //判断顺子
        for (int i=0;i<cn.length;i++){
            char now = cn[i];
            if (shun.isEmpty()){
                shun.offer(now);
            }else {
                char shunTop = shun.peek();
                if (Math.abs(shunTop-now)==1){
                    shun.offer(now);
                }else{
                    if (shun.size()>=3){
                        grade = shun.size()-2;
                    }else{
                        shun.clear();
                        shun.offer(now);
                    }
                }
            }
            if (!shun.isEmpty()){
                int length = shun.size()-2;
                if (length>grade){
                    grade = length;
                }
            }
        }
        //判断豹子
        for (int i=0;i<cn.length;i++){
            char now = cn[i];
            if (bao.isEmpty()){
                bao.offer(now);
            }else{
                char baoTop = bao.peek();
                if (baoTop==now){
                    bao.offer(baoTop);
                }else {
                    if (bao.size()>=3){
                        grade2 = bao.size()-1;
                    }else{
                        bao.clear();
                        bao.offer(now);
                    }
                }
            }
            if (!bao.isEmpty()){
                int length = bao.size()-1;
                if (length>grade2){
                    grade2 = length;
                }
            }
        }
        if (grade>grade2){
            return grade;
        }
        return grade2;
    }

    public static void main(String[] args) {
        Test t = new Test();
//        Scanner c = new Scanner(System.in);
//        String a = c.next();
//        String[] x = a.split(",");
//        t.phoneNum(x);
//        int a = (int) (Math.pow(10,9)+7);
//        System.out.println(a);
        BigDecimal m1 = BigDecimal.ONE;
        m1 = new BigDecimal(2).multiply(m1);
        System.out.println(m1);
    }



}
