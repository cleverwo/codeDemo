package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 15:32
 * @Description: 单例模式--懒汉式（线程安全）
 * 多线程环境下的懒汉式单例模式(DCL，双检锁实现)
 * 由于指令可能重排序，即DCL可能会返回一个并不完整的对象。
 */
public class SingleLan2 {
    private static SingleLan2 sl2 = null;

    private SingleLan2(){}

    //采用双重校验，只有在sl2为空时才执行synchronized代码块部分 该方式称为DCL 双检锁实现
    //由于指令可能重排序，即DCL可能会返回一个并不完整的对象。https://www.jianshu.com/p/c647fe58b98d
    public static SingleLan2 getInstance(){
        if (null == sl2){
            synchronized (SingleLan2.class){
                if (sl2 == null){
                    sl2 = new SingleLan2();
                }
            }
        }
        return sl2;
    }
}
