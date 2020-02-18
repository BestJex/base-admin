package com.jex.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.jex.utils.EncryptUtils.*;

public class EncryptUtilsTest {

    /**
     * 对称加密
     */
    @Test
    public void testDesEncrypt() {
        try {
            //assertEquals 如果预期值与真实值相等，则运行success，反之Failure
            assertEquals("7772841DC6099402", desEncrypt("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对称解密
     */
    @Test
    public void testDesDecrypt() {
        try {
            assertEquals("123456", desDecrypt("7772841DC6099402"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
