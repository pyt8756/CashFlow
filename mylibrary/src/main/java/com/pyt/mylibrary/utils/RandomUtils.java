package com.pyt.mylibrary.utils;

import java.util.Random;

/**
 * 随机工具类
 */
public class RandomUtils {

    private static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private RandomUtils() {
        throw new AssertionError();
    }

    /**
     * 得到一个固定长度的 数字，大小写字母 随机字符串，
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }

    /**
     * 得到一个固定长度的 数字 随机字符串
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * 得到一个固定长度的 大小写字母 随机字符串
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * 得到一个固定长度的 大写字母 随机字符串
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * 得到一个固定长度的 小写字母 随机字符串
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * 得到一个固定长度的随机字符串，根据source获取
     */
    public static String getRandom(String source, int length) {
        return source == null ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * 得到一个固定长度的随机字符串，sourceChar
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * 获取 大于等于0 的随机数
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * 获取 min 到 max 的随机数
     * if min > max, return 0
     * if min == max, return min
     * else return random int between min and max
     */
    public static int getRandom(int min, int max) {
        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * Shuffling algorithm, Randomly permutes the specified array using a default source of randomness
     */
    public static boolean shuffle(Object[] objArray) {
        return objArray != null && shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * 随机排列指定的数组 洗牌算法
     */
    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
            return false;
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return true;
    }

    /**
     * 随机排列指定的数组 洗牌算法
     */
    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }
        return shuffle(intArray, getRandom(intArray.length));
    }

    /**
     * 随机排列指定的数组 洗牌算法
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }
}
