package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 16:18
 * @Description: 单例模式--懒汉式
 * 多线程环境下的懒汉式单例模式(基于静态内部类实现)
 * 这种写法是最为推崇的写法，利用static final关键字的同步机制，初始化后就无法修改保证了线程安全。，
 * 使用holder的方式保证了延迟加载，不适用不会被加载。。。
 */
public class SingleLan4 {

    private SingleLan4(){
        //防止反射注入
        if(Inner.sl4 != null){
            throw new IllegalStateException();
        }
    }

    private  static class Inner{
        static final SingleLan4 sl4 = new SingleLan4();
    }

    public static SingleLan4 getInstance(){
        return Inner.sl4;
    }
}
