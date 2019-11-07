package com.example.demo.utils;

import sun.misc.BASE64Encoder;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 * @Auther: wangzhendong
 * @Date: 2019/11/7 15:18
 * @Description: Rsa加密生成公私钥方法
 */
public class KeyPairGenUtil {
    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";
    /**
     * 密钥长度，用来初始化
     */
    private static final int KEYSIZE = 1024;
    /**
     * 指定公钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /**
     * 指定私钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "PrivateKey";

    public static void main(String[] args) throws Exception {
        SecureRandom  secureRandom  =  new  SecureRandom();
        System.out.println(secureRandom.nextInt());
    }

    /**
     * 生成密钥对 存储到本地
     *
     * @throws Exception
     */
    private static void generateKeyPair() throws Exception {
        /**  RSA算法要求有一个可信任的随机数源  */
        SecureRandom  secureRandom  =  new  SecureRandom();
        /**  为RSA算法创建一个KeyPairGenerator对象  */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        /**  利用上面的随机数据源初始化这个KeyPairGenerator对象  */
        keyPairGenerator.initialize(KEYSIZE,  secureRandom);
        keyPairGenerator.initialize(KEYSIZE);
        /**  生成密匙对  */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /**  得到公钥  */
        Key publicKey = keyPair.getPublic();
        /**  得到私钥  */
        Key privateKey = keyPair.getPrivate();
        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /**  用对象流将生成的密钥写入文件  */
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally {
            /**  清空缓存，关闭文件输出流  */
            oos1.close();
            oos2.close();
        }
    }

    /**
     * 生成公私钥 打印到终端
     * @throws NoSuchAlgorithmException
     */
    private static void genKeyPair() throws NoSuchAlgorithmException {
        /**  RSA算法要求有一个可信任的随机数源  */
        SecureRandom secureRandom = new SecureRandom();
        /**  为RSA算法创建一个KeyPairGenerator对象  */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        /**  利用上面的随机数据源初始化这个KeyPairGenerator对象  */
        keyPairGenerator.initialize(KEYSIZE, secureRandom);
        //keyPairGenerator.initialize(KEYSIZE);
        /**  生成密匙对  */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        /**  得到公钥  */
        Key publicKey = keyPair.getPublic();
        /**  得到私钥  */
        Key privateKey = keyPair.getPrivate();
        byte[] publicKeyBytes = publicKey.getEncoded();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String publicKeyBase64 = new BASE64Encoder().encode(publicKeyBytes);
        String privateKeyBase64 = new BASE64Encoder().encode(privateKeyBytes);
        System.out.println("publicKeyBase64.length():" + publicKeyBase64.length());
        System.out.println("publicKeyBase64:" + publicKeyBase64);
        System.out.println("privateKeyBase64.length():" + privateKeyBase64.length());
        System.out.println("privateKeyBase64:" + privateKeyBase64);
    }
}
