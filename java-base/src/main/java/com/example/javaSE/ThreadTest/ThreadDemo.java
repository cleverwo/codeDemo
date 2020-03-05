package com.example.javaSE.ThreadTest;

import java.util.concurrent.*;

/**
 * @Auther: 10413
 * @Date: 2020/3/4 09:40
 * @Description:
 * 线程的新建方式
 */
public class ThreadDemo {

    public static void main(String[] args) {
        //1.继承Thread
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("启动线程");
            }
        };
        thread.start();
        new Threadt().start();
        //2.实现runable接口
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        thread1.start();
        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
class Threadt extends Thread{
    @Override
    public void run() {
        System.out.println("启动threadt线程");
    }
}
