package com.pyt.mylibrary.utils;

import android.os.Handler;
import android.os.Looper;


public class HandlerUtils {

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static void post(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        HANDLER.postDelayed(runnable, delayMillis);
    }

    public static void remove(Runnable runnable) {
        HANDLER.removeCallbacks(runnable);
    }
}
