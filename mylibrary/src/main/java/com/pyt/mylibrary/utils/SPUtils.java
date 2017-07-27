package com.pyt.mylibrary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * SharedPreferences工具类
 */
public class SPUtils {
    /**
     * 默认文件名
     */
    private static String DEFAULT_NAME = "config_sharedpreferences";

    /**
     * 获取SharedPreferences方法
     *
     * @param name SharedPreferences文件名
     */
    private static SharedPreferences getPreferences(String name) {
        return getApplication().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, String value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, int value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, boolean value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, float value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, long value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, Set<String> value) {
        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到默认文件名
     */
    public static void put(String key, Object value) {

        put(DEFAULT_NAME, key, value);
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, String value) {
        if (value == null) {
            getPreferences(name).edit().remove(key).commit();
        } else {
            getPreferences(name).edit().putString(key, value).commit();
        }
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, int value) {
        getPreferences(name).edit().putInt(key, value).commit();
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, boolean value) {
        getPreferences(name).edit().putBoolean(key, value).commit();
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, float value) {
        getPreferences(name).edit().putFloat(key, value).commit();
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, long value) {
        getPreferences(name).edit().putLong(key, value).commit();
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, Set<String> value) {
        getPreferences(name).edit().putStringSet(key, value).commit();
    }

    /**
     * 保存数据到指定文件名
     */
    @SuppressLint("ApplySharedPref")
    public static void put(String name, String key, Object value) {
        if (value == null) {
            getPreferences(name).edit().remove(key).commit();
        } else {
            try {
                byte[] bytes = ByteUtils.objectToByte(value);
                put(name, key, HexUtils.encodeHexStr(bytes));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static String get(String key, String defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static int get(String key, int defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static boolean get(String key, boolean defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static float get(String key, float defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static long get(String key, long defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static Set<String> get(String key, Set<String> defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从默认文件名获取保存数据
     */
    public static <T> T get(String key, T defaultObject) {
        return get(DEFAULT_NAME, key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static String get(String name, String key, String defaultObject) {
        return getPreferences(name).getString(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static int get(String name, String key, int defaultObject) {
        return getPreferences(name).getInt(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static boolean get(String name, String key, boolean defaultObject) {
        return getPreferences(name).getBoolean(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static float get(String name, String key, float defaultObject) {
        return getPreferences(name).getFloat(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static long get(String name, String key, long defaultObject) {
        return getPreferences(name).getLong(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static Set<String> get(String name, String key, Set<String> defaultObject) {
        return getPreferences(name).getStringSet(key, defaultObject);
    }

    /**
     * 从指定文件名获取保存数据
     */
    public static <T> T get(String name, String key, T defaultObject) {
        String hex = get(name, key, (String) null);
        if (hex == null) return defaultObject;
        try {
            byte[] bytes = HexUtils.decodeHex(hex.toCharArray());
            return (T) ByteUtils.byteToObject(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultObject;
    }

    /**
     * 移除默认文件名某个key值已经对应的值
     */
    public static void remove(String key) {
        remove(DEFAULT_NAME, key);
    }

    /**
     * 移除指定文件名某个key值已经对应的值
     */
    @SuppressLint("ApplySharedPref")
    public static void remove(String name, String key) {
        getPreferences(name).edit().remove(key).commit();
    }

    /**
     * 清除默认文件名所有数据
     */
    public static void clear() {
        clear(DEFAULT_NAME);
    }

    /**
     * 清除指定文件名所有数据
     */
    @SuppressLint("ApplySharedPref")
    public static void clear(String name) {
        getPreferences(name).edit().clear().commit();
    }

    /**
     * 查询默认文件名某个key是否已经存在
     */
    public static boolean contains(String key) {
        return contains(DEFAULT_NAME, key);
    }

    /**
     * 查询指定文件名某个key是否已经存在
     */
    public static boolean contains(String name, String key) {
        return getPreferences(name).contains(key);
    }

    /**
     * 返回默认文件名所有的键值对
     */
    public static Map<String, ?> getAll() {
        return getAll(DEFAULT_NAME);
    }

    /**
     * 返回指定文件名所有的键值对
     */
    public static Map<String, ?> getAll(String name) {
        return getPreferences(name).getAll();
    }

}
