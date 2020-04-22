package com.study.utils;

import java.util.Random;

public class createUtils {

    /**
     * 生成指定长度的数字验证码
     *
     * @param length
     * @return
     */
    public static String createCode(int length) {
        Random random = new Random();
        String validateCode = "";
        for (int i = 0; i < length; i++) {
            validateCode += random.nextInt(9);
        }
        return validateCode;
    }

}