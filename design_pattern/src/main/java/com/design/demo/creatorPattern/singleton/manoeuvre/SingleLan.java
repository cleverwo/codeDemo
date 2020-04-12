package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 14:05
 * @Description: 单例模式--懒汉式
 * 实例延迟加载的单例模式
 * 缺点：单线程下没有问题，多线程会出现线程安全的问题，
 *      在if判断与实例化中可能会造成多线程的同时运行，破坏了单例模式的设计
 *      if判断与实例创建没有实现原子性导致的。
 */
public class SingleLan {

    private static SingleLan sl = null;

    private SingleLan(){}

    public static SingleLan getInstance(){
        if (null == sl){
            sl = new SingleLan();
        }
        return sl;
    }
}