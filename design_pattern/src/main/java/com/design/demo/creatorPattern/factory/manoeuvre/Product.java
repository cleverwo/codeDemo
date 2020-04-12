package com.design.demo.creatorPattern.factory.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/10/11 19:25
 * @Description:
 */
public abstract class Product {

    //产品共有逻辑
    public void method(){
        //公共逻辑
        System.out.println("product start");
    }

    //抽象方法
    public abstract void method2();

}
