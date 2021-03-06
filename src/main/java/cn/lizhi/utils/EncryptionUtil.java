package cn.lizhi.utils;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
/**
 * @Author chenjiafneg
 * @Date 2020/8/5 17:25
 * @Version 1.0
 */
public class EncryptionUtil {
    public static String md5(String value) {

        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(value, Charset.forName("UTF-8"));
        return hasher.hash().toString();
    }

    public static String sha1(String value) {

        Hasher hasher = Hashing.sha1().newHasher();
        hasher.putString(value, Charset.forName("UTF-8"));
        return hasher.hash().toString();
    }

    public static String sha256(String value) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(value, Charset.forName("UTF-8"));
        return hasher.hash().toString();
    }

    public static String base64Encode(String value) {
        return Base64.encodeBase64String(value.getBytes());
    }

    public static String base64Decode(String value) {
        return new String(Base64.decodeBase64(value));
    }

    public static String bcrypt(String value) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(value);
    }

    public static String aesEncode(String content, String password) {
        return AES.encrypt(content, password);
    }

    public static String aesDecode(String content, String password) {
        return AES.decrypt(content, password);
    }
}
