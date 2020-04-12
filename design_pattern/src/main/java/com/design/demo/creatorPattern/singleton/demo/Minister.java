package com.design.demo.creatorPattern.singleton.demo;

/**
 * @Auther: wangzhendong
 * @Date: 2019/8/27 09:23
 * @Description:
 */
public class Minister {

    public static void main(String[] args) {
       //test();
       test2();
    }


    public static void test(){
        for (int day = 0; day < 3; day++) {
            Emperor emperor = Emperor.getInstance();
            emperor.say();
        }
    }
    public static void test2() {
        //定义5个大臣
        int ministerNum = 5;
        for (int i = 0; i < ministerNum; i++) {
            Emperor2 emperor = Emperor2.getInstance();
            System.out.print("第" + (i + 1) + "个大臣参拜的是：");
            emperor.say();
        }
    }
}
