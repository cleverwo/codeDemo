package OfferDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/2/21 10:36
 * @Description: 41, 和为S的联系整数序列
 */
public class Test41 {

    //穷举 一个个实验
    public ArrayList<ArrayList<Integer>> FindContinuousSequence1(int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        for (int i = 1; i < sum; i++) {
            // 临时和
            int temp = 0;
            int j = i;
            //从i开始往后加直到temp>=sum
            while (temp < sum) {
                temp += j;
                j++;
            }
            //如果找到了那么就要把数据添加到结果数据中。
            if (temp == sum) {
                ArrayList<Integer> newArray = new ArrayList<Integer>();
                for (int k = i; k < j; k++) {
                    newArray.add(k);
                }
                result.add(newArray);
            }
        }
        return result;
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        for (int i = 1; i < target; i++) {
            int tmp = 0;
            int j = i;
            while (tmp < target) {
                tmp += j;
                j++;
            }
            if (tmp == target) {
                int[] a = new int[j - i];
                int val = i;
                for (int k = 0; k < a.length; k++) {
                    a[k] = val;
                    val++;
                }
                list.add(a);
            }
        }
        int[][] res = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 答案2：
     * 双指针思路
     * 输入sum=20（1，2，3，4，5，6，7，8，9，10，11，12，13，14，15
     * 1，定义两个指针，左指针从1开始，右指针从2开始
     * 循环开始
     * 2，求和（1+2 = 3
     * 3，如果判断3小于20，右指针++，2变为3，求和3+3=6。循环一直到右指针=6，和为21。
     * 4，else if 判断21大于20，左指针++，1变为2，和减去左指针值，和为21-1=20。
     * 5，else 和与输入一样，存数。   【再把右指针++，求和，求剩余组合】
     * 循环结束
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> resp = new ArrayList<>();
        if (sum <= 0) {
            return resp;
        }
        int leftP = 1;
        int rightP = 2;
        int sumVal = leftP + rightP;
        //和小于sum
        while (sum > rightP) {
            if (sumVal < sum) {
                rightP++;
                sumVal += rightP;
            } else if (sumVal > sum) {
                sumVal -= leftP;
                leftP++;
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = leftP; i <= rightP; i++) {
                    list.add(i);
                }
                resp.add(list);

                rightP++;
                sumVal += rightP;
            }
        }

        return resp;
    }
}
