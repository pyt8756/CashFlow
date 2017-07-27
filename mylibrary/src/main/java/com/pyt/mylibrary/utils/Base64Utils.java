package com.pyt.mylibrary.utils;

import android.util.Base64;

/**
 * base64工具类
 * Created by pengyutao on 2017/6/28.
 */
public class Base64Utils {

    public static String encode(String value) {
        return encode(value, Base64.DEFAULT);
    }

    public static String encode(String value, int flag) {
        return Base64.encodeToString(value.getBytes(), flag);
    }


    public static String decode(String value) {
        return decode(value, Base64.DEFAULT);
    }

    public static String decode(String value, int flag) {
        return new String(Base64.decode(value, flag));
    }
}
