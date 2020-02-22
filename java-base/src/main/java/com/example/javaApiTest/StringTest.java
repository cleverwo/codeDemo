package com.example.javaApiTest;


import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 10413
 * @Date: 2020/1/21 21:14
 * @Description: String, StringBuffer, StringBuilder区别
 */
public class StringTest {
    public static void main(String[] args) {
        //test1();
        //test();
        //testStringBuilderAndStringBuffer();
        // stringbuffer 基本方法实验 对应api
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer("1");
        StringBuffer stringBuffer2 = new StringBuffer(14);
        StringBuffer stringBuffer3 = new StringBuffer("12345678901234567");
        char[] a2 = {'a', 'b', 'c', 'd', 'e', '0', '0', '0', '0'};
        stringBuffer.append(a2, 2, 2);
        stringBuffer.appendCodePoint(49);
        int a = stringBuffer.capacity();
        char b = stringBuffer.charAt(0);
        int c = stringBuffer.codePointAt(1);
        int d = stringBuffer.codePointBefore(2);
        int e = stringBuffer.codePointCount(0, 1);
        stringBuffer.delete(0, 1);
        stringBuffer3.ensureCapacity(44);
        stringBuffer3.getChars(0, 4, a2, 3);
        int f = stringBuffer3.indexOf("123");
        int g = stringBuffer3.indexOf("123", 8);
        stringBuffer.insert(2, "!!!!");
        int h = stringBuffer.lastIndexOf("");
        int i = stringBuffer3.lastIndexOf("123");
        int j = stringBuffer3.lastIndexOf("123", 5);
        int k = stringBuffer.lastIndexOf("", 5);
        stringBuffer3.replace(0, 5, "#####");
        stringBuffer3.reverse();
        stringBuffer3.setCharAt(0, '*');
        stringBuffer3.setLength(20);
        stringBuffer3.setLength(3);
        String l = stringBuffer3.substring(0);
        String m = stringBuffer3.substring(0, 1);
        String n = stringBuffer.toString();
        stringBuffer.trimToSize(); // 去除多余的容量，使容量和长度一致
        //string 基本方法使用
        String string = new String();
        //stringbuilder 基本方法
        StringBuilder stringBuilder = new StringBuilder();
    }

    // 测试string，stringbuffer，stringbuilder的性能
    public static void test() {
        Long start1 = System.currentTimeMillis();//获取开始时间
        for (int i = 0; i < 100000; i++)//重复10万次进行String变量加操作
        {
            // 改变成string = a+b会节约大量的时间
            String str = "a";
            str += "b";
        }
        Long end1 = System.currentTimeMillis();//获取结束时间
        System.out.println("String花费时间：" + (end1 - start1));//打印出花费的时间

        Long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)//重复10万次进行StringBuilder变量加操作
        {
            StringBuilder str2 = new StringBuilder("a");
            str2.append("b");
        }
        Long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder花费时间：" + (end2 - start2));

        Long start3 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)//重复10万次进行StringBuffer变量加操作
        {
            StringBuffer str2 = new StringBuffer("a");
            str2.append("b");
        }
        Long end3 = System.currentTimeMillis();
        System.out.println("StringBuffer花费时间：" + (end2 - start2));
    }

    // 测试string，stringbuffer，stringbuilder的性能对比
    public static void test1() {
        Long start1 = System.currentTimeMillis();//获取开始时间
        for (int i = 0; i < 100000; i++)//重复10万次进行String变量加操作
        {
            // 改变成string = a+b会节约大量的时间
            String str = "a" + "b";
//            System.out.println(str.hashCode());
//            System.out.println(System.identityHashCode(str));
        }
        Long end1 = System.currentTimeMillis();//获取结束时间
        System.out.println("String花费时间：" + (end1 - start1));//打印出花费的时间

        Long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)//重复10万次进行StringBuilder变量加操作
        {
            StringBuilder str2 = new StringBuilder("a" + "b");
            str2.append("b");
        }
        Long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder花费时间：" + (end2 - start2));

        Long start3 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)//重复10万次进行StringBuffer变量加操作
        {
            StringBuffer str2 = new StringBuffer("a" + "b");
            str2.append("b");
        }
        Long end3 = System.currentTimeMillis();
        System.out.println("StringBuffer花费时间：" + (end2 - start2));
    }

    // 测试stringbuffer和stringbuilder的线程安全
    public static void testStringBuilderAndStringBuffer() {
        //证明StringBuffer线程安全，StringBuilder线程不安全
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        CountDownLatch latch1 = new CountDownLatch(1000);
        CountDownLatch latch2 = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        stringBuilder.append(1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch1.countDown();
                    }
                }
            }).start();
        }
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        stringBuffer.append(1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch2.countDown();
                    }

                }
            }).start();
        }
        try {
            latch1.await();
            System.out.println(stringBuilder.length());
            latch2.await();
            System.out.println(stringBuffer.length());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // 打印对象的内存地址对比测试
    public static void soutHash(){
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = s1+s2;
        System.out.println(s3==s4); // false
        System.out.println(s3.hashCode()); //地址一样
        System.out.println(s4.hashCode());
        System.out.println(System.identityHashCode(s3)); //地址不同
        System.out.println(System.identityHashCode(s4));
        //s3和s4的hashCode一样，但是内存地址不一样，要想获取对象的内存地址应使用System.identityHashCode()方法
    }
}
