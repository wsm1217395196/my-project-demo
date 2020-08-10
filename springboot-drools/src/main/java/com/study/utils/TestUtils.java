package com.study.utils;

import org.springframework.util.ObjectUtils;

public class TestUtils {

    public static boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }
}
