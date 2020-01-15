package com.example;


import com.example.test1.encoding.Base64;
import sun.misc.BASE64Decoder;

import java.io.UnsupportedEncodingException;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/8 16:22
 * @Description:
 */
public class StringTest {

    /**
     * 字符串转换为16进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 16进制字符串转换为字符串 减去a
     *
     * @param s
     * @return
     */
    public static String hexStringToString2(String s, int a) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                int hexi = Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
                baKeyword[i] = (byte) (0xff & hexi - a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "gbk");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 字符串转换为16进制字符串 加1
     *
     * @param s
     * @return
     */
    public static String stringToHexString2(String s, int a) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch + a);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制表示的字符串转换为字节数组
     *
     * @param s 16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        s = s.replace(" ", "");
        int len = s.length();
        byte[] b = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个字节
            b[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return b;
    }

    /**
     * byte数组转16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    // 将字符串转成ASCII的Java方法
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }

    //将ASCII转成字符串的java方法
    public static String asciiToString(String value) {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) Integer.parseInt(chars[i]));
        }
        return sbu.toString();
    }

    public static void printHexString(String hint, byte[] b) {
        System.out.print(hint);
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println("");
    }

    static public String Hex2Text(String src) throws UnsupportedEncodingException {
        int len = src.length() / 2;
        byte b[] = new byte[len];
        for (int i = 0; i < len; i++) b[i] = (byte) Short.parseShort(src.substring(i * 2, (i + 1) * 2), 16);
        return new String(b, "UTF-8");
    }

    public static void main(String[] args) throws Exception{
//        char[] a = {'a','b','c'};
//        String test = new String(a);
//        System.out.println(test);
//        System.out.println(test.charAt(2));
        // 电信base64密文
        String test = "Y2sSi8kRXLjlq3nFQji+9YByjxur+HK/ZaiKcpjE0ZnSR5VU/Gth8miZX7jZutk29GYNwQ2MbkI9F1bAW3clE1bNE3VrJ5Hujn+DiDY/vn9H6nDZHefW12u6VDUWGE3fghrNlaSTdeEtFS8CzE2eIXdGSK7YD6OoWgi+PXN58oJmB2Yjf87tc75PvrDYp73E";
        BASE64Decoder decoder = new BASE64Decoder();
        // 解密位byte数组
        byte[] bytes = decoder.decodeBuffer(test);
        //转换为16进制 bytesToHexString在 StringUtils 里有,和AESUtils一个包下
        System.out.println(bytesToHexString(bytes));

    }
}