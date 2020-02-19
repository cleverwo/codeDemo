package com.example.test1.method2;

import com.example.test2.StringTest;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;

public class AESCipher {

    private static final String IV_STRING = "000102030405060708090a0b0c0d0e0f";
    //private static final String IV_STRING = "A-32-Byte-String";
    private static final String charset = "UTF-8";

    // aes 加密为base64编码字符串
    public static String aesEncryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        //明文转byte字节码 编码方式位utf-8
        byte[] contentBytes = content.getBytes(charset);
        //密钥转byte字节码
        byte[] keyBytes = key.getBytes(charset);
        byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);

        //System.out.println(Arrays.toString(encryptedBytes));
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedBytes);
    }
    // aes 加密为hex
    public static String aesEncryptHex(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        //明文转byte字节码 编码方式位utf-8
        byte[] contentBytes = content.getBytes(charset);
        //string密钥转byte字节码
        //byte[] keyBytes = key.getBytes(charset);
        //16进制密钥转为byte字节码
        byte[] keyBytes = StringTest.hexStringToByteArray(key);
        byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
        String out = StringTest.bytesToHexString(encryptedBytes);
        return out;
    }

    // aes 解密 base64编码
    public static String aesDecryptString(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Decoder decoder = Base64.getDecoder();
        byte[] encryptedBytes = decoder.decode(content);
        byte[] keyBytes = key.getBytes(charset);
        byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
        return new String(decryptedBytes, charset);
    }

    // aes 解密 hex
    public static String aesDecryptHex(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] encryptedBytes = StringTest.hexStringToByteArray(content);
        byte[] keyBytes = key.getBytes(charset);
        byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
        return new String(decryptedBytes, charset);
    }

    public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
    }

    private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        //string偏移量转为byte字节码
        //byte[] initParam = IV_STRING.getBytes(charset);
        //16进制偏移量转为字节码
        byte[] initParam = StringTest.hexStringToByteArray(IV_STRING);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(mode, secretKey, ivParameterSpec);

        return cipher.doFinal(contentBytes);
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        //String plainText = "abc`~!@#$%^&*()_+-=':;<>?,./|{}[]\\\"xyz123中文";
        String plainText = "{\"Addr\":\"190312001390\",\"NoticeType\":\"01\",\"Type\":\"00\",\"RegularData\":\"000000.00,000000.00,0869662038524333,000000,20190201102044,26,3.7,00\"}";
        //String key = "16BytesLengthKey";
        String key = "000102030405060708090a0b0c0d0e0f";

        System.out.println("原始字符串：" + plainText);
        System.out.println("秘钥：" + key);
        System.out.println("偏移量：" + IV_STRING);

        //String string = AESCipher.aesEncryptString(test, key);
        //System.out.println("加密后得到的 Base64 字符串：" + string);
        //String decryptedText = AESCipher.aesDecryptString(string, key);
        //System.out.println("解密后得到的字符串：" + decryptedText);
        String hex = AESCipher.aesEncryptHex(plainText,key);
        System.out.println("加密后得到的 16进制 字符串：" + hex);

       // String decryptedHex = AESCipher.aesDecryptHex(hex, key);
        //System.out.println("解密后得到的字符串：" + decryptedHex);
    }
}