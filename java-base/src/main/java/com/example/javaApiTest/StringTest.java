package com.example.javaApiTest;


import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @Auther: 10413
 * @Date: 2020/1/21 21:14
 * @Description: String, StringBuffer, StringBuilder区别
 */
public class StringTest {

    public static void stringApiTest() {
        //构造方法
        //1 初始化为空的字符串
        String s = new String();
        //2 用默认的字节码初始化字符串
        String s1 = new String(new byte[]{12, 11, 11});
        //3 String(char[] value)
        String s2 = new String(new char[]{'1', 's', 's'});
        //4 String(string)
        //5 String(Stringbuffer)
        //6 String(Stringbuilder)

        //方法：
        //	char charAt(int index) 返回 char指定索引处的值。
        //	int compareTo(String anotherString) 按字典顺序比较两个字符串。 如果相同返回0，不同返回不同位置的ASCII码的差值，如果是子串则返回长度的差值。
        String a1 = "saass";
        String a2 = "sasfdsdf";
        System.out.println(a1.codePointAt(2));
        System.out.println(a2.codePointAt(2));
        System.out.println(a1.compareTo(a2));
        //String concat(String str) 将指定字符串连接到其末尾
        String c1 = "sdf";
        String c2 = "ww";
        System.out.println(c1.concat(c2));

        /**
         *  contains 判断String 是否存在指定char
         *  方法： boolean contains(CharSequence s) 当且仅当此字符串包含指定的char值序列时才返回true。
         *      CharSequence相当于String
         */
        String cotains1 = "12.22.3";
        System.out.println(cotains1.contains("."));

        //int	indexOf(int ch) 返回指定字符第一次出现的字符串内的索引。
        //	replace(char oldChar, char newChar)返回从替换所有出现的导致一个字符串 oldChar在此字符串 newChar 。
        //String replaceAll(String regex, String replacement)用给定的替换替换与给定的 regular expression匹配的此字符串的每个子字符串。regex支持正则表达
        //String	substring(int beginIndex) 返回一个字符串，该字符串是此字符串的子字符串。
        String sub1 = "asfsdfdsf".substring(3);
        System.out.println(sub1);
        //String	substring(int beginIndex, int endIndex) 返回一个字符串，该字符串是此字符串的子字符串。

        /**
         * split 分割字符串：
         * 方法:String[]	split(String regex) 将此字符串分割为给定的 regular expression的匹配。
         *      String[]	split(String regex, int limit) 将这个字符串拆分为给定的 regular expression的匹配。
         *      limit 控制拆分完数组的长度，limit<=0,不做限制，假设最大拆分数组长度为n，则limit<=n有效，大于n则相当于n
         *  注意 分割的标点符号 有点要加\\
         */
        String[] split1 = "1.2.1".split("\\.");
        String[] split2 = "1.2.1".split("\\.", 1);
        System.out.println(Arrays.toString(split2));
    }

    public static void stringBufferTest() {
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
    }

    public static void main(String[] args) {
        String a1 = "aaa";
        String a2 = "sss";
        System.out.println(a1.codePointAt(2));
        System.out.println(a2.codePointAt(2));
        System.out.println(a1.compareTo(a2));

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
            e.printStackTrace();
        }
    }

    // 打印对象的内存地址对比测试
    public static void soutHash() {
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = s1 + s2;
        System.out.println(s3 == s4); // false
        System.out.println(s3.hashCode()); //地址一样
        System.out.println(s4.hashCode());
        System.out.println(System.identityHashCode(s3)); //地址不同
        System.out.println(System.identityHashCode(s4));
        //s3和s4的hashCode一样，但是内存地址不一样，要想获取对象的内存地址应使用System.identityHashCode()方法
    }
}
