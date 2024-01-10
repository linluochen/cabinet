package com.tools.common.encrypt;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AESUtil {
    /**
     * 密钥长度: 128, 192 or 256
     */
    private static final int KEY_SIZE = 128;
    /**
     * 加密/解密算法名称
     */
    private static final String ALGORITHM = "AES";
    /**
     * 随机数生成器（RNG）算法名称
     */
    private static final String RNG_ALGORITHM = "SHA1PRNG";
    private static final String CHARTSET_NAME = "UTF-8";

    /**
     * 生成密钥对象
     */
    private static SecretKey generateKey(byte[] key) throws Exception {
        // 创建安全随机数生成器
        SecureRandom random = SecureRandom.getInstance(RNG_ALGORITHM);
        // 设置 密钥 key 的字节数组 作为安全随机数生成器的种子
        random.setSeed(key);
        // 创建 AES 算法生成器
        KeyGenerator gen = KeyGenerator.getInstance(ALGORITHM);
        // 初始化算法生成器
        gen.init(KEY_SIZE, random);
        // 生成 AES 密钥对象, 也可以直接创建密钥对象: return new SecretKeySpec(key,ALGORITHM);
        return gen.generateKey();
    }

    /**
     * 数据加密: 明文 -> 密文
     */
    public static String encryptBase64(String plainStr, String key) throws Exception {
        // 生成密钥对象
        SecretKey secKey = generateKey(key.getBytes(CHARTSET_NAME));
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（加密模型）
        cipher.init(Cipher.ENCRYPT_MODE, secKey);
        // 加密数据, 返回密文
        byte[] plainBytes = plainStr.getBytes(CHARTSET_NAME);
        byte[] cipherBytes = cipher.doFinal(plainBytes);
        return Base64.encodeBase64String(cipherBytes);
    }

    /**
     * 数据解密: 密文 -> 明文
     */
    public static String decrypt(String secretStr, String key) throws Exception {
        byte[] cipherBytes = Base64.decodeBase64(secretStr.getBytes(CHARTSET_NAME));
        // 生成密钥对象
        SecretKey secKey = generateKey(key.getBytes());
        // 获取 AES 密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化密码器（解密模型）
        cipher.init(Cipher.DECRYPT_MODE, secKey);
        // 解密数据, 返回明文
        byte[] plainBytes = cipher.doFinal(cipherBytes);
        return new String(plainBytes, CHARTSET_NAME);
    }

    /**
     * 签名生成（签名 + 接口编码）
     */
    public static String signature(String timestamp, String interfaceNum) {
        return Hashing.sha1().hashString((timestamp + interfaceNum), Charsets.UTF_8).toString().toUpperCase();
    }

    /**
     * 时间戳生成
     */
    public static String timestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

}