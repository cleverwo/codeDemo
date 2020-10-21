package OfferDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 10:06
 * @Description: 32，把数组排成最小的数
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
 * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 */
public class Test32 {

    /**
     * 答案1：
     * 比较两个字符串s1, s2大小的时候，先将它们拼接起来，
     * 比较s1+s2,和s2+s1那个大，如果s1+s2大，那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面。
     */
    public String PrintMinNumber1(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum1 = Integer.valueOf(numbers[i] + "" + numbers[j]);
                int sum2 = Integer.valueOf(numbers[j] + "" + numbers[i]);
                if (sum1 > sum2) {
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        String str = new String("");
        for (int i = 0; i < numbers.length; i++) {
            str = str + numbers[i];
        }
        return str;
    }
    public String test1(int[] numbers){
        if (numbers==null||numbers.length==0){
            return "";
        }
        //两两比较拼接的字符大小，如果逆序拼接的小，则换位
        for (int i=0;i<numbers.length;i++){
            for (int j=i+1;j<numbers.length;j++){
                long sum1 = Long.parseLong(numbers[i]+""+numbers[j]);
                long sum2 = Long.parseLong(numbers[j]+""+numbers[i]);
                if (sum1>sum2){
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        StringBuilder stringBuilder  = new StringBuilder("");
        for (int i = 0; i < numbers.length; i++) {
            stringBuilder = stringBuilder.append(numbers[i]);
        }
        return stringBuilder.toString();
    }

    /**
     * 答案2：
     * 重写collection的 compare方法
     */
    public String PrintMinNumber2(int [] numbers) {
        ArrayList<String> arrayList = new ArrayList<String>();
        // numbers 转 string型
        for (int i : numbers) {
            arrayList.add(i + "");
        }
        Collections.sort(arrayList, new Comparator<String>() {
            @Override
            // o1 是第二元素，o2是第一个元素
            public int compare(String o1, String o2) {
                int i = 0, j = 0;
                // 比较01和02 对应字符的大小
                while (i < o1.length() && j < o2.length()) {
                    // 优先比较最左边的字符大小
                    if (o1.charAt(i) < o2.charAt(j)) {
                        // 02比o1大 o2 后移动 返回-1 原数组位【o2，o1】变为 【01，02】
                        return -1;
                    } else if (o1.charAt(i) > o2.charAt(j)) {
                        return 1;
                    }
                    i++;
                    j++;
                }
                //o2 长度到低了，用o2的长度进行延续在和o1比较
                while (i < o1.length()) {
                    if (j == o2.length()) {
                        //重置j为开始节点
                        j -= o2.length();
                    }
                    if (o1.charAt(i) < o2.charAt(j)) {
                        return -1;
                    }else if (o1.charAt(i) > o2.charAt(j)) {
                        return 1;
                    }
                    i++;
                    j++;
                }
                while (j < o2.length()) {
                    if (i == o1.length()){
                        i -= o1.length();
                    }
                    if (o1.charAt(i) < o2.charAt(j)) {
                        return -1;
                    } else if (o1.charAt(i) > o2.charAt(j)) {
                        return 1;
                    }
                    i++;
                    j++;
                }
                //相等不移动
                return 0;
            }
        });

        StringBuilder stringBuilder2 = new StringBuilder();
        for (String s : arrayList) {
            stringBuilder2.append(s);
        }
        return stringBuilder2.toString();
    }

    public static void main(String[] args) {
        Test32 t = new Test32();
        int[] a = {3,32,321};
        String out = t.PrintMinNumber2(a);
        System.out.println(out);

    }
}
