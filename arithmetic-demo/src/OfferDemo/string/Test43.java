package OfferDemo.string;

/**
 * @Auther: 10413
 * @Date: 2020/2/21 17:41
 * @Description:
 * 43,左旋转字符串
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
 * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 *
 */
public class Test43 {

    /**
     * 思路：
     * 左移动就是移位
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if (str==null||str.length()==0||n<0){
            return "";
        }
        int left = n%str.length();
        String remove = str.substring(0,left);
        StringBuffer stringBuffer = new StringBuffer(str.substring(left));
        stringBuffer.append(remove);
        return new String(stringBuffer);
    }

    public static void main(String[] args) {
        Test43 t = new Test43();
        System.out.println(t.LeftRotateString("abcdefg",2));
    }
}
