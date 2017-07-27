package com.pyt.mylibrary.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * LOG工具类
 */
public class LOG {
    public static boolean LOGED = true;
    public static String TAG = " #----> ";

    /**
     * verbose日志
     */
    public static void v(String tag, String msg) {
        v(tag, msg, true);
    }

    /**
     * info信息日志
     */
    public static void i(String tag, String msg) {
        i(tag, msg, true);
    }

    /**
     * error错误日志
     */
    public static void e(String tag, String msg) {
        e(tag, msg, true);
    }

    /**
     * debug调试日志
     */
    public static void d(String tag, String msg) {
        d(tag, msg, true);
    }

    /**
     * warning警告日志
     */
    public static void w(String tag, String msg) {
        w(tag, msg, true);
    }

    /**
     * verbose日志
     */
    public static void v(Object tag, String msg, boolean DEBUG) {
        v(tag.getClass().getSimpleName(), msg, DEBUG);
    }

    /**
     * info信息日志
     */
    public static void i(Object tag, String msg, boolean DEBUG) {
        i(tag.getClass().getSimpleName(), msg, DEBUG);
    }

    /**
     * error错误日志
     */
    public static void e(Object tag, String msg, boolean DEBUG) {
        e(tag.getClass().getSimpleName(), msg, DEBUG);
    }

    /**
     * debug调试日志
     */
    public static void d(Object tag, String msg, boolean DEBUG) {
        d(tag.getClass().getSimpleName(), msg, DEBUG);
    }

    /**
     * warning警告日志
     */
    public static void w(Object tag, String msg, boolean DEBUG) {
        w(tag.getClass().getSimpleName(), msg, DEBUG);
    }


    /**
     * verbose日志
     */
    public static void v(String tag, String msg, boolean DEBUG) {
        if (LOGED && DEBUG)
            Log.v(tag, TAG + msg);
    }

    /**
     * info信息日志
     */
    public static void i(String tag, String msg, boolean DEBUG) {
        if (LOGED && DEBUG)
            Log.i(tag, TAG + msg);
    }

    /**
     * error错误日志
     */
    public static void e(String tag, String msg, boolean DEBUG) {
        if (LOGED && DEBUG)
            Log.e(tag, TAG + msg);
    }

    /**
     * debug调试日志
     */
    public static void d(String tag, String msg, boolean DEBUG) {
        if (LOGED && DEBUG) {
            String[] lines = msg.split(System.getProperty("line.separator"));
            for (String line : lines) {
                Log.d(tag, TAG + line);
            }
        }
    }

    public static void dJson(String tag, String method, String msg) {
        if (LOGED) {
            String[] lines = msg.split(System.getProperty("line.separator"));
            for (String line : lines) {
                Log.d(tag, method + TAG + line);
            }
        }
    }

    /**
     * warning警告日志
     */
    public static void w(String tag, String msg, boolean DEBUG) {
        if (LOGED && DEBUG)
            Log.w(tag, TAG + msg);
    }


    /**
     * 以级别为 w 的形式输出Throwable
     */
    public static void w(Throwable tr) {
        w("", tr);
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    public static void w(String msg, Throwable tr) {
        if (LOGED) {
            if (null != msg) {
                Log.w(TAG, "", tr);
            } else {
                Log.w(TAG, msg, tr);
            }
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(Throwable tr) {
        e("", tr);
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String msg, Throwable tr) {
        if (LOGED) {
            if (null != msg) {
                Log.e(TAG, "", tr);
            } else {
                Log.e(TAG, msg, tr);
            }
        }
    }


    /**
     * 把Log存储到文件中
     *
     * @param log  需要存储的日志
     * @param path 存储路径
     */
    public static void log2File(String log, String path) {
        log2File(log, path, true);
    }

    public static void log2File(String log, String path, boolean append) {
        synchronized (LOG.class) {
            FileUtils.writeFile(log + "\r\n", path, append);
        }
    }

    public static void json(String tag, String method, String json) {
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(2);
                dJson(tag, method, message);
                return;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(2);
                dJson(tag, method, message);
                return;
            }
            e(tag, "Invalid Json");
        } catch (JSONException e) {
            e(tag, "Invalid Json");
        }
    }

    public static void log(LogLevel level, String TAG, String message, boolean debug) {
        switch (level) {
            case V:
                v(TAG, message, debug);
                break;
            case D:
                d(TAG, message, debug);
                break;
            case I:
                i(TAG, message, debug);
                break;
            case W:
                w(TAG, message, debug);
                break;
            case E:
                e(TAG, message, debug);
                break;
        }
    }

    public enum LogLevel {
        V, D, I, W, E
    }

}
