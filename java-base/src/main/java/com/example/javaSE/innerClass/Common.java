package com.example.javaSE.innerClass;

import com.example.javaSE.ComparaTest;

/**
 * @Auther: 10413
 * @Date: 2020/3/1 20:19
 * @Description:
 * 普通内部类
 */
public class Common {
    public int outField1 = 1;
    protected int outField2 = 2;
    int outField3 = 3;
    private int outField4 = 4;

    public Common() {
        // 在外部类对象内部，直接通过 new InnerClass(); 创建内部类对象
        CommonInner innerObj = new CommonInner();
        System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
        System.out.println("其内部类的 field1 字段的值为: " + innerObj.field1);
        System.out.println("其内部类的 field2 字段的值为: " + innerObj.field2);
        System.out.println("其内部类的 field3 字段的值为: " + innerObj.field3);
        System.out.println("其内部类的 field4 字段的值为: " + innerObj.field4);
    }

    public class CommonInner{
        public int field1 = 5;
        protected int field2 = 6;
        int field3 = 7;
        private int field4 = 8;
        //static int field5 = 5; // 编译错误！普通内部类中不能定义 static 属性

        public CommonInner() {
            System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
            System.out.println("其外部类的 outField1 字段的值为: " + outField1);
            System.out.println("其外部类的 outField2 字段的值为: " + outField2);
            System.out.println("其外部类的 outField3 字段的值为: " + outField3);
            System.out.println("其外部类的 outField4 字段的值为: " + outField4);
            /*ComparaTest comparaTest = new ComparaTest();
            comparaTest.comparatorTest();*/
        }

        /*public static void test(){

        }*/
    }


    public static void main(String[] args) {
        Common outerObj = new Common();
        // 不在外部类内部，使用：外部类对象. new 内部类构造器(); 的方式创建内部类对象
        CommonInner innerObj = outerObj.new CommonInner();
    }
}
