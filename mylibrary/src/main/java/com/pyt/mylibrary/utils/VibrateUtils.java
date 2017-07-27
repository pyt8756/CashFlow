package com.pyt.mylibrary.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * 震动
 * 权限
 * android.permission.VIBRATE
 */
public class VibrateUtils {

    /**
     * 震动指定时间
     */
    public static void vibrate(Context context, long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(milliseconds);
    }

    /**
     * 振动用给定的模式
     */
    public static void vibrate(Context context, long[] pattern, int repeat) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeat);
    }

}
