package com.example.javaSE;

import com.example.javaSE.modal.AbstractTest;
import com.example.javaSE.modal.InterfaceTest;

/**
 * @Auther: 10413
 * @Date: 2020/4/4 16:56
 * @Description:
 * 抽象类和接口的区别
 */
public class Test_02 implements InterfaceTest {

    public static void main(String[] args) {
        AbstractTest a = new AbstractTest() {
            @Override
            public void a() {
                System.out.println("aa");
            }
        };

    }

    @Override
    public void a() {

    }

    @Override
    public void aa() {

    }
}


