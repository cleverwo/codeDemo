package com.example.javaSE;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/4/4 16:14
 * @Description:
 * 泛型的上下界
 */
public class Test_01 {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        C c = new C();
        Object[] objects = new Object[]{b,c};
        List<? extends B> list1 = new ArrayList<>();  // 上界
        List<? super B> list2 = new ArrayList<>();    // 下界
        Object o = new Object();
        // list1.add(o); 不能添加任何元素，因为List中具体是B的哪种子类无法确定
        // list1.add(a);
        // list1.add(b);
        //list1.add(c);
        o = list1.get(0);
        a = list1.get(0);
        b = list1.get(0);
        //c = list1.get(0); //编译错误，编译器无法向下转型
        // list2.add(o); 编译错误，
        // list2.add(a); 因为List中具体是B的哪种父类无法确定
        list2.add(b);
        list2.add(c);
        o = list2.get(0);
        // a = list2.get(0); 编译错误，因为List中具体是B的哪种父类无法确定，无法向下转型，而Object是所有类的父类
        // b = list2.get(0);
        // c = list2.get(0);
    }

}

class A {
}

class B extends A {
}

class C extends B {
}

