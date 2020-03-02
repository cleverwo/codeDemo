package com.example.javaSE;

import com.example.javaSE.modal.Father;
import com.example.javaSE.modal.Son;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/2/11 17:00
 * @Description:
 * javaSE 基础部分演示代码
 *
 */
public class Demo1 {

    public static void main(String[] args) {
        Demo1 demo = new Demo1();
        demo.test8();
    }
    // hashcode 实验
    public void test1(){
        // hashcode 默认打印的是对象的内存地址
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = o1;
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        System.out.println(o3.hashCode());

        System.out.println(o1.equals(o2));
        System.out.println(o1.equals(o3));
    }
    // == 比较实验 equals 的实验
    public void test2() {
        int a = 11;
        int b = 11;
        //比较值
        System.out.println(a == b);
        Integer aa = new Integer(11);
        Integer bb = new Integer(11);
        //比较内存地址
        System.out.println(aa == bb);
        //比较值
        System.out.println(a == aa);
        //equals 比较
        System.out.println(aa.equals(bb));
    }
    // hashmap 重写equals 和hashcode 是的实质
    public void test3(){
        HashMap<String,String> map = new HashMap<>();
    }
    // int 和integer的自动拆装箱
    public void test4(){
        Integer a = new Integer(3);
        Integer b = 3; //将3自动装箱成Integer
        int c = 3;
        System.out.println(a==b);
        System.out.println(a==c);//将a自动拆箱成int在于c比较
    }
    //int基本数据类型和 Integer包装类的区别
    public void test5(){
        int i;
    }
    //string 测试
    public void test6(){
        String str1 = "HelloFlyapi";
        String str2 = "HelloFlyapi";
        String str3 = new String("HelloFlyapi");
        String str4 = "Hello";
        String str5 = "Flyapi";
        String str6 = "Hello" + "Flyapi";
        String str7 = str4 + str5;
        System.out.println("str1 == str2 result: " + (str1 == str2));
        System.out.println("str1 == str3 result: " + (str1 == str3));
        System.out.println("str1 == str6 result: " + (str1 == str6));
        System.out.println("str1 == str7 result: " + (str1 == str7));
        System.out.println("str1 == str7.intern() result: " + (str1 == str7.intern()));
        System.out.println("str3 == str3.intern() result: " + (str3 == str3.intern()));
        System.out.println("str7 == str7.intern() result: " + (str7 == str7.intern()));
    }
    // 重写 实验
    public void test7(){
        Father father = new Father();
        Father son = new Son();
        father.a();
        father.b();
        father.c();
        son.a();
        son.b();
        son.c();
    }
    //类装载器
    public void test8(){
        List<Integer> integerList = new ArrayList<>();
        List<String> stringList = new ArrayList<>();
        Class inte = integerList.getClass();
        Class str = stringList.getClass();
        System.out.println(inte.equals(str));
    }
}
