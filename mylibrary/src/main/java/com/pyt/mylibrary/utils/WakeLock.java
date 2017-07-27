package com.pyt.mylibrary.utils;

import android.content.Context;
import android.os.PowerManager;

/**
 * 亮屏
 * 权限
 * <uses-permission android:name="android.permission.WAKE_LOCK"/>
 */
public class WakeLock {
    private String TAG = this.getClass().getSimpleName();

    private PowerManager powerManager;
    private PowerManager.WakeLock wakeLock;

    public WakeLock(Context context, String tag) {
        ////获取电源的服务 声明电源管理器
        powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.FULL_WAKE_LOCK, tag);
    }

    public boolean isScreenOn() {
        return powerManager.isScreenOn();
    }

    public void turnScreenOn() {
        //点亮亮屏
        LOG.i(TAG, "PowerManager.WakeLock : wakeLock.isHeld: " + wakeLock.isHeld());
        if (!wakeLock.isHeld()) {
            LOG.i(TAG, "PowerManager.WakeLock : 点亮屏幕");
            wakeLock.acquire();
        }
    }

    public void turnScreenOff() {
        //释放亮屏
        LOG.i(TAG, "PowerManager.WakeLock : wakeLock.isHeld: " + wakeLock.isHeld());
        if (wakeLock.isHeld()) {
            LOG.i(TAG, "PowerManager.WakeLock : 灭掉屏幕");
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void release() {
        if (wakeLock != null && wakeLock.isHeld()) {
            try {
                wakeLock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PowerManager.WakeLock getWakeLock() {
        return wakeLock;
    }

    public void setWakeLock(PowerManager.WakeLock wakeLock) {
        this.wakeLock = wakeLock;
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }

    public void setPowerManager(PowerManager powerManager) {
        this.powerManager = powerManager;
    }
}
