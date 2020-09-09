package com.design.demo.creatorPattern.proxy.staticProxy;

/**
 * @Auther: 10413
 * @Date: 2020/9/3 15:45
 * @Description:
 *
 * 代理类
 */
public class ProxySubjects extends Subjects{

    private RealSubjects real = new RealSubjects();
    public void operate() {
        // 调用目标之前可以做相关操作
        System.out.println("before....");
        real.operate();
        // 调用目标之后可以做相关操作
        System.out.println("after....");
    }
}
