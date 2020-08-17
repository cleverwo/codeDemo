package exam.zijie;

/**
 * @Auther: 10413
 * @Date: 2020/8/16 10:05
 * @Description:
 * 8月16号 10点场
 * 二进制-16进制转换， 不能出现0010
 * 输入t 表示输入几个字符串，之后依次输入 16进制 字符串s， s中不能出现0010，问最少删去多少字符可以保证成立
 * eg：
 * 输入：
 * 2
 * 0123456789ABCDF
 * 001023456789ABCDEF00
 * 输出：
 * 0
 * 1
 */
public class Test01 {
    public static void main(String[] args) {
        String s = "a0010f";
        int before = s.length();
        s  = s.replaceAll("001010","00110");
        s  = s.replaceAll("0010","000");
        int after = s.length();
        System.out.println(before-after);
    }
}