package com.example.javaSE;

/**
 * @Auther: 10413
 * @Date: 2020/2/17 18:37
 * @Description:
 * java 参数传递Demo 演示代码
 * 对象的传递是引用传递，也就是传地址，原始类型数据（int,String等）传递是值传递，也就是拷贝一份传进去
 */
public class Test {
    public int money;
    public void amethod(int i) {
         System.out.println("方法得到的i的值：" + i);
         i = i * 5;
         System.out.println("方法执行语句i=i*5后i的值：" + i);
         System.out.println("money的值：" + this.money);
     }

    Time time;
    private void method(Time t) {
        System.out.println("参数t的原属性值： hour=" + t.hour);
        //这里传入的对象t，引用传递，传递的是原对象time的内存地址
        System.out.println("对t和time进行==比较，结果为：" + (t == this.time));
        //结果为true，表示t和time 的内存地址是一致的
        System.out.println("改变t的实例变量值为44");
        t.hour = 44;
    }

    Time time1;
    Time time2;
    private void swap(Time t1, Time t2) {
        t1.hour = 22;
        t2.hour = 33;
        Time temp = t1;
        // 这里可以证明，t1 和传入的this.time 不是一个对象，只是将t1中引用的值地址进行了传递
        // 这里t1 和 t2 对象发生了转换，不影响外部中 this.t1 和 this.t2的值
        // 但 一运行t1.hour = 13.. 就改变了元对象hour 引用的地址值的改变，则外部对象的值发送了改变，而对象没变
        t1 = t2;
        t2 = temp;
        System.out.println("swap 方法中t1的值变了 "+t1.hour);
        System.out.println("swap 方法中t2的值变了 "+t2.hour);
        //t1.hour = 13;
        //t2.hour = 23;
        /**
         * 但是我们从结果中发现调用该方法后输出结果并没有发生改变。
         * 说明该方法并没有对opt.time1和opt.time2这两个引用进行交换。
         * 在该方法中只是对参数t1,t2进行交换，
         * 验证了上个例子中引用传递的是内存空间地址值，并非参数就是该引用（即传的不是引用）。
         * 对参数的操作并不会影响原来的引用，只会影响参数和引用所指的同一个内存空间里面的内容。
         * 这就证明了引用类型参数传递并不是传引用，而只是传该引用的内存地址值。
         */
    }

    public static void main(String[] args) {
        Test pt = new Test();
        // 方法amethod的参数传递时值传递，对原传入的值pt.money是没有影响的
        pt.money = 100;
        pt.amethod(pt.money);
        /* 结果输出：
         * 方法得到的i的值：100
         * 方法执行语句i=i*5后i的值：500
         * money的值：100
         */

        System.out.println();
        //对象之间的参数传递
        pt.time = new Time();
        pt.time.hour = 11;
        System.out.println("time的属性值： hour=" + pt.time.hour);
        //将对象引用作为参数传递给方法method
        pt.method(pt.time);
        //对比执行方法后的变化
        System.out.println("执行方法后的time的属性值 hour=" + pt.time.hour);

        System.out.println();
        //对象之间的参数传递 传递的是其本身还是地址
        pt.time1 = new Time();
        pt.time2 = new Time();
        pt.time1.hour = 2;
        pt.time2.hour = 3;
        System.out.println("交换前的值：");
        System.out.println("time1.hour=" + pt.time1.hour);
        System.out.println("time2.hour=" + pt.time2.hour);

        pt.swap(pt.time1, pt.time2);
        System.out.println("交换后的值：");
        System.out.println("time1.hour=" + pt.time1.hour);
        System.out.println("time2.hour=" + pt.time2.hour);
    }
}
class Time {
    public int hour;
}