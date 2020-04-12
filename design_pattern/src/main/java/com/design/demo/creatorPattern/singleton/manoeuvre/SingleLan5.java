package com.design.demo.creatorPattern.singleton.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/7/25 16:58
 * @Description: 单例模式---采用枚举的单例模式
 */
public class SingleLan5 {

    private SingleLan5(){}
    public static SingleLan5 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton {
        INSTANCE;
        private SingleLan5 singleton;

        //JVM会保证此方法绝对只调用一次
        private Singleton() {
            singleton = new SingleLan5();
        }

        public SingleLan5 getInstance() {
            return singleton;
        }
    }
}
