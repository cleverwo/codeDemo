package com.example;

/**
 * @Auther: 10413
 * @Date: 2020/9/30 10:46
 * @Description:
 */
public class ClassB extends ClassA implements Cloneable{
    public static void a(){
        System.out.println("classB");
    }

    @Override
    public void c(){}
//    public final void b(){}

    public static void main(String[] args) {
        ClassA a = new ClassB();
        Object o = new Object();
    }
}
