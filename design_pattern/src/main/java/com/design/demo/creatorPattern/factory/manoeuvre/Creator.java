package com.design.demo.creatorPattern.factory.manoeuvre;

/**
 * @Auther: wangzhendong
 * @Date: 2019/10/12 10:29
 * @Description:
 */
public abstract class Creator {

    //实例化产品
    public abstract <T extends Product> T createProduct(Class<T> c);
}
