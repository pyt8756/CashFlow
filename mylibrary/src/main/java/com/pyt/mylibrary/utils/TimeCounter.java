package com.pyt.mylibrary.utils;


/**
 * 计时器
 */
public class TimeCounter {

    private static final String TAG = TimeCounter.class.getSimpleName();
    private long t;

    public TimeCounter() {
        start();
    }

    /**
     * 开始
     */
    public long start() {
        t = System.currentTimeMillis();
        return t;
    }

    /**
     * 获取时间并重新开始
     */
    public long durationRestart() {
        long now = System.currentTimeMillis();
        long d = now - t;
        t = now;
        return d;
    }

    /**
     * 获取时间
     */
    public long duration() {
        return System.currentTimeMillis() - t;
    }

    /**
     * 打印时间
     */
    public void printDuration(String tag) {
        LOG.i(TAG, tag + " :  " + duration());
    }

    /**
     * 打印时间并重新开始
     */
    public void printDurationRestart(String tag) {
        LOG.i(TAG, tag + " :  " + durationRestart());
    }
}