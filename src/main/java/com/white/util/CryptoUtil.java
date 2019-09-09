package com.white.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


@Slf4j
public class CryptoUtil {

    /**
     * 验证密码
     */
    public static boolean verifyPassword(String hashedPassword, String rawPassword, String passwordSalt)  {
        if (hashedPassword.equals(hashPassword(rawPassword, passwordSalt))) {
            return true;
        }
        return false;
    }

    /**
     * @author duanlsh
     * @description 密码加密
     * @date 2019/7/19
     **/
    public static String hashPassword(String paswword, String salt) {
        byte[] passwordByte = null;
        byte[] saltByte = null;
        String hashPassword = null;
        try {
            /**16进度**/
            passwordByte = paswword.getBytes("UTF-16LE");
            /**base64**/
            saltByte = DatatypeConverter.parseBase64Binary(salt);

            byte[] passwordAndSalt = new byte[saltByte.length + passwordByte.length];

            System.arraycopy(saltByte, 0, passwordAndSalt, 0, saltByte.length);
            System.arraycopy(passwordByte, 0, passwordAndSalt, saltByte.length, passwordByte.length);
            //SHA 256 算法
            hashPassword = computeHash(passwordAndSalt);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return hashPassword;
    }


    /**
     * add zhuwei
     * SHA 256 算法
     */
    private static String computeHash(byte[] input) {

        // SHA 加密开始
        // 创建加密对象 并傳入加密類型
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error("加密资金密码时失败："+e.getMessage());
            log.error(e.getMessage(),e);
        }
        // 得到 byte 類型结果
        byte[] byteBuffer = messageDigest.digest(input);

        return Base64.encodeBase64String(byteBuffer);
    }

    /**
     * 生成加密的盐
     *
     * @return
     */
    public static String createRandomSalt() {
        long start = System.currentTimeMillis();
        String passwordSalt = null;
        try {
            int saltSize = 64;

            Random random = new Random();
            ByteBuffer buffer = ByteBuffer.allocate(saltSize);

            for(int i=0;i<8;i++) {
                buffer.putLong(random.nextLong());
            }

            byte [] salt = buffer.array();
            passwordSalt = Base64.encodeBase64String(salt);
            log.info("generateSaltExpendTime:{}",(System.currentTimeMillis()-start));
        } catch (Exception e) {
            log.error("createRandomSalt error", e);
        }
        return passwordSalt;
    }

    public static void main(String[] args) {
//        tO3KcDXIegeLLCxdqvb8BvvCvA6s9mv60mxxXIliqDc=

        // "00lWiyi8zqzdLIxmMQq8ru95XqS1of21PAsq3+1g3GY="
        // "6AWeqDVdjZ0+6b6UdOFwXuYln9HdLfg4RhM+NtxpClfVua9/vIj6cDXwK2pZ0mnbc4lo4B37pOZ5nIke1P8pKQ=="
        String p = "123456";
//        String salt = createRandomSalt();
        String salt = "KwnnYcDgRozx2J/qHS6XFs6eW8eYWScpgBE9k5FDlIXmXMx6WJu4XXMWJtVgu9IR6Bq5BgQlZ57qKbz6zJdr9g==";
        System.out.println("salt: " + salt);
        String password = hashPassword(p, salt);
        System.out.println("password: " + password);

        System.out.println("result:: " + verifyPassword(password, p, salt));
    }

}
