package com.example.javaSE.objectTest;

/**
 * @Auther: 10413
 * @Date: 2020/3/21 10:50
 * @Description:
 */
public class ObjectTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Father a = new Father();
        Father b = (Father) a.clone();
        System.out.println(a==b);
    }
}
