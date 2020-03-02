package com.example.javaSE;

/**
 * @Auther: 10413
 * @Date: 2020/3/1 17:07
 * @Description:
 * javaSE static变量测试
 */
public class OverRideAndOverLoad {

    public String name;
    public static int age;
    public static String staticField = "静态成员变量";

    public OverRideAndOverLoad(){
        System.out.println("无参构造函数Constructor of Demo2");
    }

    public OverRideAndOverLoad(String myName, int myAge){
        this.name = myName;
        age = myAge;
        System.out.println("有参构造函数Constructor of Demo2");
    }

    public static void setStaticField(String value){
        staticField = value;
        System.out.println("staticField: " + staticField);
    }

    public static void printStaticField(){
        System.out.println("staticField: " + staticField);
    }

    public void printNameAndAge(){
        //非静态成员方法可访问 静态 和 非静态 成员变量
        System.out.println("name: " + name + " age: " + age);
    }

    public static void setAge(int n){
        //静态成员方法，仅可访问静态成员变量,不可访问普通成员变量
        age = n;
//        name = "abc";
    }

    public static void printAge(){
        System.out.println("age: " + age);
    }

    public static void main(String[] args){
        System.out.println("age of TestStatic");
        System.out.println(OverRideAndOverLoad.age); //静态成员可通过类名直接调用
        OverRideAndOverLoad.printAge();  //静态方法可通过类名直接调用
        //TestStatic.printNameAndAge();  //非静态方法不可通过类名直接调用

        OverRideAndOverLoad.printStaticField();
        OverRideAndOverLoad.setStaticField("haha");

        OverRideAndOverLoad testStatic1 = new OverRideAndOverLoad();
        System.out.println("name and age of testStatic1");
        testStatic1.printNameAndAge();  //非静态成员仅通过类实例调用
        testStatic1.printAge();  //静态方法可通过类实例调用
    }
}
