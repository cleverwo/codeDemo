package com.design.demo.creatorPattern.singleton.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: wangzhendong
 * @Date: 2019/8/27 09:51
 * @Description: 单例模式拓展 固定的实例
 */
public class Emperor2 {
    //定义最多能产生的实例数量
    private static final int maxNumOfEmperor = 2;
    //每个皇帝都有名字，使用一个ArrayList来容纳，每个对象的私有属性
    private static List<String> nameList = new ArrayList<>();
    //定义一个列表，容纳所有的皇帝实例
    private static List<Emperor2> emperorList = new ArrayList<>();
    // 当前皇帝序号
    private static int countNumOfEmperor = 0;

    //产生所有的对象
    static {
        for (int i = 0; i < maxNumOfEmperor; i++) {
            emperorList.add(new Emperor2("皇" + (i + 1) + "帝"));
        }
    }

    private Emperor2(String name) {
        nameList.add(name);
    }

    private Emperor2() {
        //世俗和道德约束你，目的就是不产生第二个皇帝
    }

    public static Emperor2 getInstance(){
        Random random = new Random();
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return emperorList.get(countNumOfEmperor);
    }

    public static void say(){
        System.out.println(nameList.get(countNumOfEmperor) + "批阅" );
    }

}
