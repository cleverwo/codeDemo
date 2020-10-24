package com.example;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

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

    public static void main(String[] args) throws InterruptedException {
        ExceptionTest t = new ExceptionTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3);
        t1.start();

       t1.run();
        t1.run();
    }
}
