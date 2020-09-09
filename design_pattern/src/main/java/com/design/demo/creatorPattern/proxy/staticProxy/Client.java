package com.design.demo.creatorPattern.proxy.staticProxy;

/**
 * @Auther: 10413
 * @Date: 2020/9/3 15:45
 * @Description:
 * 客户端
 */
public class Client {


    public static void main(String[] args) {
        Subjects subjects = new ProxySubjects();
        subjects.operate();
    }
}
