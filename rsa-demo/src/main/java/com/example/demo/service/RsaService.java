package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @Auther: wangzhendong
 * @Date: 2019/11/7 15:08
 * @Description:
 */
@Service
@Slf4j
public class RsaService {
    public static String PrivateKeySessionAttr = "pwdPrivateKey";

    /**
     * String to hold name of the security provider.
     */
    //public static final String PROVIDER = "BC";

    /**
     * String to hold name of the encryption algorithm.
     */
    public static final String ALGORITHM = "RSA";

    /**
     * String to hold name of the encryption padding.
     */
    public static final String PADDING = "RSA/None/PKCS1Padding";


    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 密钥长度，用来初始化
     */
    private static final int KEY_SIZE = 512;


    /**
     * 生成密钥对
     *
     * @return:
     * @auther: wangzhendong
     * @date: 2018/11/10 15:04
     */
//    public KeyPair generateKeyPair() {
//        try {
//            /** 为RSA算法创建一个KeyPairGenerator对象 */
//            final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
//            keyPairGenerator.initialize(KEY_SIZE);
//            final KeyPair keyPair = keyPairGenerator.generateKeyPair();
//            if (null == keyPair) {
//                log.error("generateKeyPair error: keyPair is null");
//                return Result.returnError("generateKeyPair error: keyPair is null");
//            }
//            log.info("generateKeyPair success: publicKey => {}", keyPair.getPublic());
//            return Result.returnSuccess(keyPair);
//        } catch (Exception e) {
//            log.error("generateKeyPair error: {}", e.getMessage());
//            return Result.returnError("generateKeyPair error: " + e.getMessage());
//        }
//    }

    /**
     * @param request     向服务器的请求
     * @param cryptograph 需要解密的密文
     * @return: 解密密文
     * @auther: wangzhendong
     * @date: 2018/11/10 15:28
     */
//    public Result<String> decryptPwd(HttpServletRequest request, String cryptograph) {
//        /* step1 从session中获取到请求的私钥 */
//        HttpSession session = request.getSession();
//        RSAPrivateKey parivateKey = (RSAPrivateKey) session.getAttribute(PrivateKeySessionAttr);
//        if (null == parivateKey) {
//            log.error("decryptPwd failed: privateKey is null");
//            return Result.returnError("decryptPwd error: privateKey is null");
//        }
//        /* step2 用私钥解密密文 */
//        Base64 base64 = new Base64();
//        Result<String> ret = decrypt(base64.decode(cryptograph), parivateKey);
//        if (!ret.success()) {
//            log.error("decryptPwd failed: {}", ret.msg());
//            return com.watermeter.framework.result.Result.returnError("decryptPwd failed:" + ret.msg());
//        }
//        String decodedPwd = ret.value();
//        /* step3 返回解密的密文 */
//        return Result.returnSuccess(decodedPwd);
//    }


    /**
     * 用私钥解密
     *
     * @param text 密文
     * @param key  私钥
     * @return 解密后的密文
     */
//    public synchronized Result<String> decrypt(byte[] text, RSAPrivateKey key) {
//        byte[] decryptedText = null;
//        try {
//            final Cipher cipher = Cipher.getInstance(ALGORITHM);
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            decryptedText = cipher.doFinal(text);
//            log.info("decrypt bytes size: {}", decryptedText.length);
//            return Result.returnSuccess(new String(decryptedText));
//        } catch (Exception e) {
//            log.error("decrypt error: {}", e.getMessage());
//            return Result.returnError("decrypt error:" + e.getMessage());
//        }
//    }


    /**
     * 用公钥解密
     *
     * @param text
     * @param key
     * @return
     */
//    public synchronized Result<String> encrypt(String text, RSAPublicKey key) {
//        byte[] encryptedText = null;
//        try {
//            final Cipher cipher = Cipher.getInstance(PADDING);
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            encryptedText = cipher.doFinal(text.getBytes());
//            log.info("encrypt bytes size:" + encryptedText.length);
//            // use String to hold cipher binary data
//            Base64 base64 = new Base64();
//            String cipherTextBase64 = base64.encodeToString(encryptedText);
//            return Result.returnSuccess(cipherTextBase64);
//        } catch (Exception e) {
//            log.error("decrypt error:" + e);
//            return Result.returnError("decrypt error:" + e);
//        }
//    }

}
