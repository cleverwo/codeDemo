package com.example.javaSE.modal;

/**
 * @Auther: 10413
 * @Date: 2020/2/29 17:49
 * @Description:
 */
public class Son extends Father {
    public void a(){
        System.out.println("son a method");
    }

    public void b(){
        super.b();
        System.out.println("son b method");
    }
}
