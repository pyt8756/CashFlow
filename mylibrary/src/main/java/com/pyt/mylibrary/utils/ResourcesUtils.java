package com.pyt.mylibrary.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import static com.pyt.mylibrary.BaseApplication.getContext;


/**
 * 资源工具类
 * Created by pengyutao on 2017/5/25.
 */
public class ResourcesUtils {

    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字
     */
    public static String getString(int resId, Object... formatArgs) {
        return getResources().getString(resId, formatArgs);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static float getDimens(int resId) {
        return getResources().getDimension(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

}
