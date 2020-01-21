package OfferDemo;

/**
 * @Auther: 10413
 * @Date: 2020/1/21 20:55
 * @Description:
 * 2.替换空格
 *请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Test2 {

    // 暴力破解
    public static String replaceSpace(StringBuffer str) {
       for (int i =0;i<str.length();i++){
           char c = str.charAt(i);
           if (c ==' '){
               str.replace(i,i+1,"%20");
               i+=2;
           }
       }
       return str.toString();
    }

    // string自带的函数
    public static String replaceSpace1(StringBuffer str){
        return str.toString().replaceAll(" ","%20");
    }

    // 新建一个数组，碰到空格用%20代替
    public static String replaceSpace2(StringBuffer str){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            if (c ==' '){
                sb.append("%20");
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer("We    A   re Happy");
        System.out.println(replaceSpace(stringBuffer));
    }
}
