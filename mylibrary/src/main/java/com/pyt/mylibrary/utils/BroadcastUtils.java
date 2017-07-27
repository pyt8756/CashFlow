package com.pyt.mylibrary.utils;

import android.content.Context;
import android.content.Intent;

/**
 * 广播工具
 * Created by pengyutao on 2017/2/23.
 */
public class BroadcastUtils {

    /**
     * 发送广播
     */
    public static void send(Context context, String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        context.sendBroadcast(intent);
    }

}
