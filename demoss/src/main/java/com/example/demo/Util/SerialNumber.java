package com.example.demo.Util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SerialNumber {
    /**
     * 生成随机数
     *
     * @return
     */
    public static String getRandom() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + RandomStringUtils.randomNumeric(4);
    }
}
