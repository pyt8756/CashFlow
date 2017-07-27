package com.pyt.mylibrary.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪切板工具类
 */
public class ClipboardUtils {

    private static ClipboardManager getManager(Context context) {
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    /**
     * 复制内容到剪切板
     */
    public static void copyToClipboard(Context context, String text) {
        getManager(context).setPrimaryClip(ClipData.newPlainText(null, text));
    }

    /**
     * 获取剪切板内容个数
     */
    public static int getItemCount(Context context) {
        ClipData data = getManager(context).getPrimaryClip();
        return data.getItemCount();
    }

    public static String getText(Context context, int index) {
        ClipData clip = getManager(context).getPrimaryClip();
        if (clip != null && clip.getItemCount() > index) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }

    public static String getLatestText(Context context) {
        ClipData clip = getManager(context).getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return String.valueOf(clip.getItemAt(0).coerceToText(context));
        }
        return null;
    }
}
