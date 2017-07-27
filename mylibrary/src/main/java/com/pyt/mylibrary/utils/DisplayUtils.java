package com.pyt.mylibrary.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 显示工具类
 */
public class DisplayUtils {

    private static final String TAG = DisplayUtils.class.getSimpleName();

    /**
     * 获取 显示信息
     */
    public static DisplayMetrics getDisplayMetrics() {
        return ResourcesUtils.getResources().getDisplayMetrics();
    }

    public static int getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return getDisplayMetrics().heightPixels;
    }

    /**
     * dip转换px
     */
    public static int dip2px(float dip) {
        final float scale = getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(float px) {
        final float scale = getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        final float fontScale = getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 打印 显示信息
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics();
        String sb = "_______  显示信息:  " +
                "\ndensity         :" + dm.density +
                "\ndensityDpi      :" + dm.densityDpi +
                "\nheightPixels    :" + dm.heightPixels +
                "\nwidthPixels     :" + dm.widthPixels +
                "\nscaledDensity   :" + dm.scaledDensity +
                "\nxdpi            :" + dm.xdpi +
                "\nydpi            :" + dm.ydpi;
        LOG.i(TAG, sb);
        return dm;
    }
}
