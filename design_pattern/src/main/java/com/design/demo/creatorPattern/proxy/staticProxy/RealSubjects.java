package com.design.demo.creatorPattern.proxy.staticProxy;

/**
 * @Auther: 10413
 * @Date: 2020/9/3 15:45
 * @Description:
 * 实体类
 */
public class RealSubjects extends Subjects{
    public void operate() {
        System.out.println("do something...");
    }
}
