package com.example.javaSE.innerClass;

/**
 * @Auther: 10413
 * @Date: 2020/3/2 11:27
 * @Description:
 * 静态内部类演练
 */
public class StaticClass {
    public int outfield1 = 111;
    public static int outfield2 = 1222;

    public StaticClass() {
        System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
        // 创建静态内部类对象
        System.out.println("其外部类的 field1 字段的值为: " + outfield1);
        StaticInnerClass innerObj = new StaticInnerClass();
        System.out.println("其内部类的 field1 字段的值为: " + innerObj.field1);
        System.out.println("其内部类的 field2 字段的值为: " + innerObj.field2);
        System.out.println("其内部类的 field3 字段的值为: " + innerObj.field3);
        System.out.println("其内部类的 field4 字段的值为: " + innerObj.field4);
        System.out.println("其内部类的 field5 字段的值为: " + StaticInnerClass.field5);
    }

    static class StaticInnerClass {
        public int field1 = 1;
        protected int field2 = 2;
        int field3 = 3;
        private int field4 = 4;
        // 静态内部类中可以定义 static 属性
        static int field5 = 5;

        public StaticInnerClass() {
            System.out.println("创建 " + StaticInnerClass.class.getSimpleName() + " 对象");
            //System.out.println("其外部类的 outfield1 字段的值为: " + outfield1); // 编译错误！！
            System.out.println("其外部类的 outfield2 字段的值为: " + outfield2);
        }
        public void test(){
            StaticClass staticClass = new StaticClass();
        }
    }

    public static void main(String[] args) {
        // 无需依赖外部类对象，直接创建内部类对象
        StaticClass.StaticInnerClass staticClassObj = new StaticClass.StaticInnerClass();
        StaticInnerClass innerClass = new StaticInnerClass();
        StaticClass outerObj = new StaticClass();
    }
}
