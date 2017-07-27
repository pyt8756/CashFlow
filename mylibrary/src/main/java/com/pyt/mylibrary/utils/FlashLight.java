package com.pyt.mylibrary.utils;

import android.hardware.Camera;
import android.os.Handler;

/**
 * 闪光灯
 * 权限
 * <uses-permission android:name="android.permission.FLASHLIGHT"/>
 * <uses-permission android:name="android.permission.CAMERA"/>
 */
public class FlashLight {

    private static Camera camera;
    private static Handler handler = new Handler();

    /**
     * 超过3分钟自动关闭，防止损伤硬件
     */
    private static final int OFF_TIME = 3 * 60 * 1000;

    public static boolean turnOnFlashLight() {
        if (camera == null) {
            camera = Camera.open();
            camera.startPreview();
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameter);
            handler.removeCallbacksAndMessages(null);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    turnOffFlashLight();
                }
            }, OFF_TIME);
        }
        return true;
    }

    public static boolean turnOffFlashLight() {
        if (camera != null) {
            handler.removeCallbacksAndMessages(null);
            Camera.Parameters parameter = camera.getParameters();
            parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameter);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
        return true;
    }
}
