package com.example.javaSE.modal;

/**
 * @Auther: 10413
 * @Date: 2020/4/4 16:58
 * @Description:
 */
public interface InterfaceTest {
    int a = 10;
    public void a();
    void aa();
    default void b(){
        System.out.println(a);
    }
}
