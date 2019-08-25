package com.qezhhnjy.login.util;

import com.qezhhnjy.login.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author fuzy
 * create time 19-3-28-下午9:40
 */
public class EncryptUtil {
    private static RandomNumberGenerator generator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME = "md5";
    public static final int HASH_ITERATIONS = 2;

    public static void encrypt(User user) {
        // 随机字符串作为salt因子，实际参与运算的salt我们还引入其它干扰因子
        user.setSalt(generator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), HASH_ITERATIONS).toHex();
        user.setRealName(user.getPassword());
        user.setPassword(newPassword);
    }
}
