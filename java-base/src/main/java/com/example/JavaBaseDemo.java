package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 10413
 * @Date: 2020/9/29 09:27
 * @Description:
 *
 * 实验性代码
 */
public class JavaBaseDemo {

    // 泛型的类型擦除
    public void judgeGenericErasure() throws Exception{
        List<Integer> intList = new ArrayList<>();
        intList.add(11);
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        System.out.println(intList.getClass());
        System.out.println(stringList.getClass());
        // 原始类型相等
        System.out.println(intList.getClass() == stringList.getClass());
        // 反射注入intList String类型的参数
        intList.getClass().getMethod("add", Object.class).invoke(intList, "asd");
        for (int i=0;i<intList.size();i++){
            System.out.println(intList.get(i));
        }
    }

    // 拆装箱
    public void checkoutNumber(){
        Integer a = 1;
        int b =2;
        Integer B = Integer.valueOf(b);
        int A = a.intValue();
        System.out.println(B.getClass());
        System.out.println(A);
    }

    public static void main(String[] args) {
        JavaBaseDemo demo = new JavaBaseDemo();
        try {
            demo.checkoutNumber();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
