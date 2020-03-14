package syncBase;

import com.sun.org.apache.xpath.internal.operations.String;

/**
 * @Auther: 10413
 * @Date: 2020/3/7 15:05
 * @Description:
 * 锁消除 和 锁粗化
 */
public class Test {
    public void test(String s1,String s2){
        StringBuffer buffer = new StringBuffer();
        buffer.append(s1).append(s2);
    }

    public void test(String str){
        int i=0;
        StringBuffer buffer = new StringBuffer();
        while(i<100){
            buffer.append(str);
            i++;
        }
        System.out.println(buffer.toString());
    }
}
