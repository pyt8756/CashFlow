package com.pyt.mylibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;


/**
 * 权限工具类
 * Created by pengyutao on 2017/2/9.
 */
public class PermissionUtils {

    /**
     * 检测权限
     */
    public static boolean requestPermissions(Fragment fragment, String[] permission, String toast, int requestCode) {
        return requestPermissions(null, fragment, permission, toast, requestCode);
    }

    /**
     * 检测权限
     */
    public static boolean requestPermissions(Activity activity, String[] permission, String toast, int requestCode) {
        return requestPermissions(activity, null, permission, toast, requestCode);
    }

    /**
     * 检测权限
     */
    private static boolean requestPermissions(Activity activity, Fragment fragment, String[] permission,
                                              String toast, int requestCode) {
        Context context = null;
        if (activity != null) {
            context = activity;
        } else if (fragment != null) {
            context = fragment.getContext();
        }

        if (context == null) {
            throw new NullPointerException("Activity 或 Fragment 有一个不能为null");
        }

        if (hasPermissions(context, permission)) {
            return true;
        } else {
            if (activity != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.requestPermissions(permission, requestCode);
                }
            } else {
                fragment.requestPermissions(permission, requestCode);
            }
            if (!StringUtils.isEmpty(toast)) {
                ToastUtils.showToast(toast);
            }
            return false;
        }
    }

    /**
     * 是否有具有权限
     *
     * @param perms 权限列表
     */
    public static boolean hasPermissions(@NonNull Context context, @NonNull String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        for (String perm : perms) {
            boolean hasPerm = (PermissionChecker.checkSelfPermission(context, perm) ==
                    PackageManager.PERMISSION_GRANTED);
            if (!hasPerm) {
                return false;
            }
        }
        return true;
    }
}
