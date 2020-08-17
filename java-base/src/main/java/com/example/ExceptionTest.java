package com.example;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * @Auther: 10413
 * @Date: 2020/4/25 11:57
 * @Description:
 */
public class ExceptionTest {

    public void test(){
        try{
            throw new IOException();
        }catch (Exception e){
            System.out.println("io 异常");
        }
    }

    public static void main(String[] args) {
        ExceptionTest t = new ExceptionTest();
        t.test();
    }
}
