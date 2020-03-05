package com.example.javaSE.hashMapTest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Auther: 10413
 * @Date: 2020/3/3 18:23
 * @Description:
 */
public class Iteration {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        // 增加：add() 将指定对象存储到容器中
        list.add("计算机网络");
        list.add("现代操作系统");
        list.add("java编程思想");
        list.add("java核心技术");
        list.add("java语言程序设计");
        System.out.println(list);
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String next = (String) it.next();
            System.out.println(next);
        }

    }
}
