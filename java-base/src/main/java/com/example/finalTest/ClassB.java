package com.example.finalTest;

/**
 * @Auther: 10413
 * @Date: 2020/4/25 11:43
 * @Description:
 */
public class ClassB extends FinalA{
    // public final 修饰的方法A不能重写 private B 修饰的可以重新定义
//    public void A(){
//
//    }

    public void B(){
        System.out.println("Class B public test");
    }
}
