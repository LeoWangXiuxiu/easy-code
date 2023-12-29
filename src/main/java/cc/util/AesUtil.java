package cc.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
    public static final String ENCODE = "UTF-8";
    public static final String CIPHER_ALGORITHM = "AES";
    private static final String KEY_MAC = "HmacMD5";
    public static final String CIPHER_ALGORITHM_INS = "AES/ECB/PKCS5Padding";
    public static final String Key = "4j4KU4RquLjkcFy69Nq8fuU73ZylEy2quXmzkoYNXH5CkjCgalf/+HctxtVtrEVV1+WYYQYrN55nceYbj21jXA==";
    /**
     * AES加密
     * @param keyStr 密钥
     * @param dataStr 原始数据
     */
    public static String encrypt(String keyStr,String dataStr) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        SecretKey secretKey = new SecretKeySpec(decoder.decodeBuffer(keyStr), CIPHER_ALGORITHM);
        //Cipher完成加密或解密工作类
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_INS);
        //对Cipher初始化，解密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密data
        byte[] cipherByte = cipher.doFinal(dataStr.getBytes(ENCODE));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(cipherByte);
    }

    /**
     * 签名
     * @param key 密钥
     * @param plaintext 原文
     */
    public static String sign(String key,String plaintext) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        SecretKey secretKey = new SecretKeySpec(decoder.decodeBuffer(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        byte[] ret = mac.doFinal(plaintext.getBytes(ENCODE));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(ret);
    }

}
