package com.design.demo.creatorPattern.singleton;


import com.design.demo.creatorPattern.singleton.manoeuvre.Single;
import com.design.demo.creatorPattern.singleton.manoeuvre.SingleLan2;

/**
 * @Auther: wangzhendong
 * @Date: 2019/9/28 16:05
 * @Description:
 */
public class SingletonDemo {
    public static void main(String[] args) {
        soutSingleLan();
    }

    /**
     * @return 测试单例模式饿汉式是否成立
     * @auther wangzhendong
     * @date 2019/9/28 16:37
     */
    public static void soutSingle(){
        Single s1 = Single.getInstance();
        Single s2 = Single.getInstance();
        // 比较s1与s2 内存唯一标识
        System.out.println("s1:"+s1.hashCode()+" s2:"+s2.hashCode());
        System.out.println("s1:"+System.identityHashCode(s1)+" s2:"+System.identityHashCode(s2));
        System.out.println("s1:"+s1.toString()+" s2:"+s2.toString());
    }

    public static void soutSingleLan(){
        SingleLan2 s1 = SingleLan2.getInstance();
        SingleLan2 s2 = SingleLan2.getInstance();
        System.out.println("s1:"+s1.hashCode()+" s2:"+s2.hashCode());
        System.out.println("s1:"+System.identityHashCode(s1)+" s2:"+System.identityHashCode(s2));
        System.out.println("s1:"+s1.toString()+" s2:"+s2.toString());
    }



}
