package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 16:13
 * @Description: 单例模式--懒汉式线程安全实例
 * 多线程环境下的懒汉式单例模式(DCL，双检锁+volatile实现)
 * 加入了volatile变量来禁止指令重排序 对于volatile关键字和指令重排序可以查看volatile的特性中有介绍
 */
public class SingleLan3 {

    private static volatile SingleLan3 sl3 = null;

    private SingleLan3(){}

    public static SingleLan3 getInstance(){
        if (null == sl3){
            synchronized (SingleLan3.class){
                if (null == sl3){
                    sl3 = new SingleLan3();
                }
            }
        }
        return sl3;
    }
}
