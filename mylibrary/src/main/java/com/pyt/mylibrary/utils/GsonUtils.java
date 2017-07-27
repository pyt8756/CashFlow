package com.pyt.mylibrary.utils;

import android.annotation.SuppressLint;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@SuppressLint("SimpleDateFormat")
public class GsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GsonUtils() {

    }

    /**
     * 将对象转换成json格式
     */
    public static String objectToJson(Object ts) {
        String jsonStr = null;
        if (gson != null) {
            jsonStr = gson.toJson(ts);
        }
        return jsonStr;
    }

    /**
     * 将json格式转换成list对象，并准确指定类型
     */
    public static <T> List<T> jsonToList(String jsonStr, Type type) {
        List<T> objList = null;
        if (gson != null) {
            objList = gson.fromJson(jsonStr, type);
        }
        return objList;
    }

    /**
     * 将json格式转换成map对象
     */
    public static Map<?, ?> jsonToMap(String jsonStr) {
        Map<?, ?> objMap = null;
        if (gson != null) {
            Type type = new com.google.gson.reflect.TypeToken<Map<?, ?>>() {
            }.getType();
            objMap = gson.fromJson(jsonStr, type);
        }
        return objMap;
    }

    /**
     * 将json转换成bean对象
     */
    public static <T> T jsonToBean(String jsonStr, Type type) {
        T obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, type);
        }
        return obj;
    }

    /**
     * 将json转换成bean对象
     */
    public static Object jsonToBean(String jsonStr, Class<?> cl) {
        Object obj = null;
        if (gson != null) {
            obj = gson.fromJson(jsonStr, cl);
        }
        return obj;
    }
}
