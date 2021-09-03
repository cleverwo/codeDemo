package com.example.javaBaseOne;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: 10413
 * @Date: 2020-10-30 10:26
 * @Description:
 */
public class Test4 {

    public static void main(String[] args) {
        NumberFormat number = NumberFormat.getNumberInstance();
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        NumberFormat percent = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(number.format(x));
        System.out.println(currency.format(x));
        System.out.println(percent.format(x));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        Test4 t = null;
        Test4 t1 = null;
        boolean mark = Objects.deepEquals(list,list2);
        boolean mark1 = Objects.deepEquals(t,t1);
        System.out.println(mark);
        System.out.println(mark1);

    }
}
