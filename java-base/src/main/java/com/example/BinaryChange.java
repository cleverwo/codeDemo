package com.example;

import com.example.test1.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/1/31 12:14
 * @Description:
 * java基础操作进制转换
 */
public class BinaryChange{
    /**
     * 进制转换主要看javaAPi种的Integer操作类
     * 进制转换用短除法计算
     */
    public static int DecimalToBinary(int num){
        int count = 0;
        if (num < 0){
            // 负数当前
            num = -num;
            count++;
        }
        while(num !=0){
            int i = num %2;
            if (i == 1){
                count ++;
            }
            num = num/2;
        }
        Integer.toBinaryString(num);
        return count;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-11));
        /*System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(11&1));
        System.out.println(1<<32);*/
        System.out.println(Integer.parseInt("1011",2));
        Integer.parseInt("1000",2);

    }


}
