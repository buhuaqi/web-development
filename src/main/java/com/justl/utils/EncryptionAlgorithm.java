package com.justl.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 用户密码加密算法SHA-512（已弃用）
 * 使用leancloud自带的加密算法
 *
 * @author buhuaqi
 * @date 2018-10-29 15:42
 */
@Deprecated
public class EncryptionAlgorithm {
    public static String encrypt(String password, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        password = salt + password;

        MessageDigest messageDisgest = MessageDigest.getInstance("SHA-512");
        messageDisgest.update(password.getBytes());

        byte byteBuffer[] = messageDisgest.digest();

        for (int i = 0; i < 512; i++) {
            byteBuffer = messageDisgest.digest(byteBuffer);
        }

        return new String(Base64.encodeBase64(byteBuffer), "UTF-8");

    }

}
