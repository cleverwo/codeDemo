package com.example.compareTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * @Auther: 10413
 * @Date: 2020/2/19 11:17
 * @Description:
 * 测试
 */
public class TestMain {
    public static void main(String[] args) {
        ArrayList<Test> list = new ArrayList<>();
        Test t1 = new Test("t1",11);
        Test t2 = new Test("t2",13);
        Test t3 = new Test("t3",3);
        Test t4 = new Test("t4",122);
        Test t5 = new Test("t5",0);
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        Collections.sort(list);
        System.out.println(list.toString());
    }
}
