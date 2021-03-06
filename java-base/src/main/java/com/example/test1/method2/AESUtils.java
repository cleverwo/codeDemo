package com.example.test1.method2;

import com.example.test1.StringTest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Auther: wangzhendong
 * @Date: 2020/1/9 10:12
 * @Description: 对称加密算法AES
 */
public class AESUtils {
    private AESUtils(){

    }
    private static final String UTF_FORMAT = "utf-8";
    private static final String AES_FORMAT = "AES";
    private static final String KEY_DEFAULT = "000102030405060708090a0b0c0d0e0f";
    private static final String IV_DEFAULT = "000102030405060708090a0b0c0d0e0f";
    private static final String KEY = "1234567891234567";
    private static final String IV = "1234567891234567";

    public static String encrypt(String content,String key,String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = content.getBytes(UTF_FORMAT);
            //进行填充
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), AES_FORMAT);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            return new sun.misc.BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String desEncrypt(String content,String key,String iv){
        try {
            byte[] encrypted1 = new sun.misc.BASE64Decoder().decodeBuffer(content);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(UTF_FORMAT), AES_FORMAT);
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes(UTF_FORMAT));

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            return new String (original);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *  16进制加密
     * @param content string字符串
     * @return 16进制的加密密文
     */
    public static String encryptAEX(String content) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = content.getBytes(UTF_FORMAT);
            //进行填充
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                // 填充位blockSize的倍数
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }
            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
            byte[] keybyte = StringTest.hexStringToByteArray(KEY_DEFAULT);
            byte[] ivbyte = StringTest.hexStringToByteArray(IV_DEFAULT);
            System.out.println(keybyte);
            System.out.println(ivbyte);
            SecretKeySpec keyspec = new SecretKeySpec(keybyte, AES_FORMAT);
            IvParameterSpec ivspec = new IvParameterSpec(ivbyte);
            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);
            String result = StringTest.bytesToHexString(encrypted);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密 16进制
     * @param content 16进制的密文
     * @return string类型的明文
     */
    public static String desEncryptAEX(String content){
        try {
            byte[] encrypted1 = StringTest.hexStringToByteArray(content);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            byte[] keybyte = StringTest.hexStringToByteArray(KEY_DEFAULT);
            byte[] ivbyte = StringTest.hexStringToByteArray(IV_DEFAULT);
            SecretKeySpec keyspec = new SecretKeySpec(keybyte, AES_FORMAT);
            IvParameterSpec ivspec = new IvParameterSpec(ivbyte);
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted1);
            //System.out.println(StringTest.bytesToHexString(original));
            return new String (original);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args){
        // 代加密明文
//        String out = "{\"Addr\":\"190312001390\",\"NoticeType\":\"01\",\"Type\":\"00\",\"RegularData\":\"000000.00,000000.00,0869662036253083,000000,20200330135648,85,3.6,00\"}";
        String out = "{\"Addr\":\"190312001390\",\"NoticeType\":\"01\",\"Type\":\"00\",\"RegularData\":\"000000.00,000000.00,0869662036253083,000000,20200330135648,85,3.6,00\"}";
        // 带解密密文
//        String in = "636B128BC9115CB8E5AB79C54238BEF580728F1BABF872BF65A88A7298C4D1990453C0B33697A1F18EA5193E1D33A8FEB8DB7F045A48EC5E8FCE8E41D22BD5B0";
//        String in = "636B128BC9115CB8E5AB79C54238BEF580728F1BABF872BF65A88A7298C4D199D2479554FC6B61F268995FB8D9BAD936F4660DC10D8C6E423D1756C05B77251356CD13756B2791EE8E7F8388363FBE7F47EA70D91DE7D6D76BBA543516184DDFFC0F5E4BCBA8D34708D4B0EAD10ED55ADC6E4EF0E67FE243B4CF79052948E89F2DC3C4D052BDFCE968342263E1A0236B";
        // 加密
//        String security = encryptAEX(out);
//        System.out.println("密文："+ security);
//        //解密
//        String end = desEncryptAEX(security);
//        System.out.println("明文："+end);


        String aa = encrypt(out,KEY,IV);
        System.out.println(aa);
    }

}

