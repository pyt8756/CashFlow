package com.pyt.mylibrary.utils;

import android.widget.Toast;

import com.pyt.mylibrary.BaseApplication;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * 吐司工具
 */
public class ToastUtils {

    private static Toast mToast = null;

    public static void showToast(final String str) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                detectionToast();
                mToast.setText(str);
                mToast.show();
            }
        });
    }

    private static void detectionToast() {
        if (mToast == null) {
            mToast = Toast.makeText(getApplication(), "", Toast.LENGTH_SHORT);
        }
    }

    public static void showToast(final int resId) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                detectionToast();
                mToast.setText(resId);
                mToast.show();
            }
        });
    }

    public static void showDebug(final String str) {
        HandlerUtils.post(new Runnable() {
            @Override
            public void run() {
                if (BaseApplication.isDebug()) {
                    detectionToast();
                    mToast.setText(str);
                    mToast.show();
                }
            }
        });
    }
}
