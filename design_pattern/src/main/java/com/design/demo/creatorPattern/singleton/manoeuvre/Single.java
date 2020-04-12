package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 14:02
 * @Description: 单例模式 -- 饿汉式
 * 缺点：没有实现延时加载，懒汉式实现了延时加载
 */
public class Single {
    private static final Single s = new Single();

    private Single() {
    }

    public static Single getInstance() {
        return s;
    }
}
