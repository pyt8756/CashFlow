package com.pyt.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * intentUtils
 * Created by pengyutao on 16/5/21.
 */
public class IntentUtils {

    /**
     * 打开拨号
     *
     * @param phoneNum 电话号码
     */
    public static void dialPhoneNum(Context context, String phoneNum) {
        if (context == null || StringUtils.isEmpty(phoneNum)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
    }
}
