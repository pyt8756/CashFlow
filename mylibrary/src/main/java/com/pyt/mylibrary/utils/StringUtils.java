package com.pyt.mylibrary.utils;

import android.text.TextUtils;

import com.pyt.mylibrary.utils.io.Charsets;
import com.pyt.mylibrary.utils.io.stream.StringBuilderWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtils {

    private StringUtils() {
    }

    /**
     * 获取安全的字符串
     */
    public static String getSafeString(String string) {
        return TextUtils.isEmpty(string) ? "" : string;
    }

    /**
     * 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0 || str.toString().trim().length() == 0;
    }

    /**
     * 是否email
     */
    public static boolean isEmail(String strEmail) {
        String strPattern = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 是否qq号
     */
    public static boolean isQQNumber(String mobiles) {
        Pattern p = Pattern.compile("^[1-9][0-9]{4,}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 是否电话
     */
    public static boolean isTelephone(String strEmail) {
        String strPattern = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

    /**
     * 是否手机号
     */
    public static boolean isMobilephone(String mobiles) {
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        }
        if (mobiles.startsWith("86") || mobiles.startsWith("+86")) {
            mobiles = mobiles.substring(mobiles.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean passWordValid(String passWord) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(passWord);
        return m.matches();
    }

    /**
     * 是否ip
     */
    public static boolean isIp(String value) {
        if (TextUtils.isEmpty(value)) {
            return false;
        }
        String[] fields = value.split("\\.");
        if (fields.length != 4) {
            return false;
        }
        boolean result = true;
        for (String field : fields) {
            try {
                int number = Integer.parseInt(field);
                result = (number >= 0 && number <= 255);
            } catch (NumberFormatException e) {
                result = false;
            }
            if (!result) {
                break;
            }
        }
        return result;
    }


    /**
     * 转换为int
     */
    public static Integer toInt(String intStr) {
        return toInt(intStr, -1);
    }

    /**
     * 转换为int，如果转换失败，返回默认值
     */
    public static int toInt(String intStr, int defValue) {
        try {
            return Integer.parseInt(intStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 转换为long
     */
    public static long toLong(String longStr) {
        return toLong(longStr, -1);
    }

    /**
     * 转换为long，如果转换失败，返回默认值
     */
    public static long toLong(String longStr, long defValue) {
        try {
            return Long.parseLong(longStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 转换为float
     */
    public static float toFloat(String fStr) {
        return toFloat(fStr, -1);
    }

    /**
     * 转换为float，如果转换失败，返回默认值
     */
    public static float toFloat(String fStr, float defValue) {
        try {
            return Float.parseFloat(fStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 转换为double
     */
    public static double toDouble(String dStr) {
        return toDouble(dStr, -1);
    }

    /**
     * 转换为double，如果转换失败，返回默认值
     */
    public static double toDouble(String dStr, double defValue) {
        try {
            return Double.parseDouble(dStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defValue;
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     */
    public static int chineseLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }

    /**
     * 格式数字
     */
    public static String formatValue(String value) {
        String result = "0";
        if (!StringUtils.isEmpty(value)) {
            try {
                double v = Double.valueOf(value);
                return formatValue(v);
            } catch (Exception e) {
                LOG.e(e);
            }
        }
        return result;
    }

    /**
     * 格式数字
     */
    public static String formatValue(double value) {
        String result;
        long divisor = 0;
        String end = "";
        if (value > 100000000000L || value < -100000000000L) {
            divisor = 100000000000L;
            end = "千亿";
        } else if (value > 100000000 || value < -100000000) {
            divisor = 100000000;
            end = "亿";
        } else if (value > 10000 || value < -10000) {
            divisor = 10000;
            end = "万";
        }
        if (divisor > 0) {
            DecimalFormat df = new DecimalFormat("#.00");
            double temp = value / divisor;
            result = df.format(temp) + end;
        } else {
            DecimalFormat df = new DecimalFormat("#");
            result = df.format(value);
        }
        return result;
    }

    /**
     * 通过年月日获取时间字符串 1999-01-01
     */
    public static String getTimeString(int year, int month, int day) {
        return year + "-" + getTimeString(month) + "-" + getTimeString(day);
    }

    /**
     * int转换为时间，如 1 => "01"  10 => "10"
     */
    private static String getTimeString(int date) {
        if (date < 10) {
            return "0" + date;
        } else {
            return String.valueOf(date);
        }
    }

    /**
     * 检测是否有emoji表情
     */
    public static boolean containsEmoji(String source) {
        if (!isEmpty(source)) {
            int len = source.length();
            for (int i = 0; i < len; i++) {
                char codePoint = source.charAt(i);
                if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji,比较的单个字符
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return codePoint == 0x0 || codePoint == 0x9 || codePoint == 0xA || codePoint == 0xD ||
                codePoint >= 0x20 && codePoint <= 0xD7FF || codePoint >= 0xE000 && codePoint <= 0xFFFD;
    }

    /**
     * 获取对象内存id
     */
    public static String getIdentity(Object obj) {
        return obj == null ? "null" : Integer.toHexString(System.identityHashCode(obj)) + " ";
    }

    /**
     * 获取 InputStream 的内容，返回 String。
     */
    public static String toString(InputStream input) throws IOException {
        return toString(input, Charset.defaultCharset());
    }

    /**
     * 获取 InputStream 的内容，返回 String。
     */
    public static String toString(InputStream input, Charset encoding) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        IOUtils.copy(input, sw, encoding);
        return sw.toString();
    }

    /**
     * 获取 InputStream 的内容，返回 String。
     */
    public static String toString(InputStream input, String encoding)
            throws IOException {
        return toString(input, Charsets.toCharset(encoding));
    }

    /**
     * 获取 Reader 的内容，返回 String。
     */
    public static String toString(Reader input) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        IOUtils.copy(input, sw);
        return sw.toString();
    }

    /**
     * 获取 URI 的内容，返回 String。
     */
    public static String toString(URI uri) throws IOException {
        return toString(uri, Charset.defaultCharset());
    }

    /**
     * 获取 URI 的内容，返回 String。
     */
    public static String toString(URI uri, Charset encoding) throws IOException {
        return toString(uri.toURL(), Charsets.toCharset(encoding));
    }

    /**
     * 获取 URI 的内容，返回 String。
     */
    public static String toString(URI uri, String encoding) throws IOException {
        return toString(uri, Charsets.toCharset(encoding));
    }

    /**
     * 获取 URL 的内容，返回 String。
     */
    public static String toString(URL url) throws IOException {
        return toString(url, Charset.defaultCharset());
    }

    /**
     * 获取 URL 的内容，返回 String。
     */
    public static String toString(URL url, Charset encoding) throws IOException {
        InputStream inputStream = url.openStream();
        try {
            return toString(inputStream, encoding);
        } finally {
            inputStream.close();
        }
    }

    /**
     * 获取 URL 的内容，返回 String。
     */
    public static String toString(URL url, String encoding) throws IOException {
        return toString(url, Charsets.toCharset(encoding));
    }

    /**
     * 获取 byte[] 的内容，返回 String。
     */
    @Deprecated
    public static String toString(byte[] input) throws IOException {
        return new String(input);
    }

    /**
     * 获取 byte[] 的内容，返回 String。
     */
    public static String toString(byte[] input, String encoding) throws IOException {
        return new String(input, encoding);
    }


}
