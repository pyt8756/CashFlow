package com.pyt.mylibrary.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;

import java.lang.reflect.Method;
import java.util.List;

import static com.pyt.mylibrary.BaseApplication.getApplication;


public class AppUtils {

    /**
     * 调用系统分享
     */
    public static void shareToOtherApp(Context context, String title, String content, String dialogTitle) {
        Intent intentItem = new Intent(Intent.ACTION_SEND);
        intentItem.setType("text/plain");
        intentItem.putExtra(Intent.EXTRA_SUBJECT, title);
        intentItem.putExtra(Intent.EXTRA_TEXT, content);
        intentItem.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intentItem, dialogTitle));
    }

    /**
     * need < uses-permission android:name =“android.permission.GET_TASKS” />
     * 判断是否前台运行
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(1);
        if (taskList != null && !taskList.isEmpty()) {
            ComponentName componentName = taskList.get(0).topActivity;
            if (componentName != null && componentName.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取本应用的VersionCode
     */
    public static int getVersionCode() {
        PackageInfo info = PackageUtils.getPackageInfo("");
        if (info != null) {
            return info.versionCode;
        } else {
            return -1;
        }
    }

    /**
     * 获取本应用的VersionName
     */
    public static String getVersionName() {
        PackageInfo info = PackageUtils.getPackageInfo("");
        if (info != null) {
            return info.versionName;
        } else {
            return "";
        }
    }

    /**
     * 手机低内存运行阀值，单位为byte
     */
    public static long getThresholdMemory() {
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.threshold;
    }

    /**
     * 手机是否处于低内存运行
     */
    public static boolean isLowMemory() {
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.lowMemory;
    }

    /**
     * 获取手机剩余内存，单位为byte
     */
    public static long getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        return info.availMem;
    }

    /**
     * 获取手机总内存，单位为byte
     */
    public static long getTotalMemory() {
        long size = 0;
        String path = "/proc/meminfo";// 系统内存信息文件
        try {
            String totalMemory = FileUtils.readProperties(path, "MemTotal", null);// 读出来是带单位kb的，并且单位前有空格，所以去掉最后三位
            if (!StringUtils.isEmpty(totalMemory) && totalMemory.length() > 3) {
                size = Long.valueOf(totalMemory.substring(0, totalMemory.length() - 3)) * 1024;
            }
        } catch (Exception e) {
            LOG.e(e);
        }
        return size;
    }

    /**
     * 获取单个应用最大分配内存，单位为byte
     */
    public static long getOneAppMaxMemory() {
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager.getMemoryClass() * 1024 * 1024;
    }

    /**
     * 获取指定包名应用占用的内存，单位为byte
     */
    public static long getUsedMemory(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            packageName = getApplication().getPackageName();
        }
        long size = 0;
        ActivityManager activityManager = (ActivityManager) getApplication().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runapps = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runapp : runapps) { // 遍历运行中的程序
            if (packageName.equals(runapp.processName)) {// 得到程序进程名，进程名一般就是包名，但有些程序的进程名并不对应一个包名
                // 返回指定PID程序的内存信息，可以传递多个PID，返回的也是数组型的信息
                Debug.MemoryInfo[] processMemoryInfo = activityManager.getProcessMemoryInfo(new int[]{runapp.pid});
                // 得到内存信息中已使用的内存，单位是K
                size = processMemoryInfo[0].getTotalPrivateDirty() * 1024;
            }
        }
        return size;
    }

    /**
     * 获取手机内部可用空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getAvailableInternalMemorySize() {
        long availableSpace = -1L;
        try {
            String path = Environment.getDataDirectory().getPath();// 获取 Android 数据目录
            StatFs stat = new StatFs(path);// 一个模拟linux的df命令的一个类,获得SD卡和手机内存的使用情况
            long blockSize = stat.getBlockSize();// 返回 Int ，大小，以字节为单位，一个文件系统
            long availableBlocks = stat.getAvailableBlocks();// 返回 Int ，获取当前可用的存储空间
            availableSpace = availableBlocks * blockSize;
        } catch (Exception e) {
            LOG.e(e);
        }
        return availableSpace;
    }

    /**
     * 获取手机内部空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getTotalInternalSpace() {
        long totalSpace = -1L;
        try {
            String path = Environment.getDataDirectory().getPath();
            StatFs stat = new StatFs(path);
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();// 获取该区域可用的文件系统数
            totalSpace = totalBlocks * blockSize;
        } catch (Exception e) {
            LOG.e(e);
        }
        return totalSpace;
    }

    /**
     * 获取外部存储可用空间，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getExternalSpace() {
        long availableSpace = -1L;
        if (FileUtils.isSDCardMounted()) {
            try {
                String path = Environment.getExternalStorageDirectory().getPath();
                StatFs stat = new StatFs(path);
                availableSpace = stat.getAvailableBlocks() * (long) stat.getBlockSize();
            } catch (Exception e) {
                LOG.e(e);
            }
        }
        return availableSpace;
    }

    /**
     * 获取手机外部可用空间大小，单位为byte
     */
    @SuppressWarnings("deprecation")
    public static long getExternalTotalSpace() {
        long totalSpace = -1L;
        if (FileUtils.isSDCardMounted()) {
            try {
                String path = Environment.getExternalStorageDirectory().getPath();// 获取外部存储目录即 SDCard
                StatFs stat = new StatFs(path);
                long blockSize = stat.getBlockSize();
                long totalBlocks = stat.getBlockCount();
                totalSpace = totalBlocks * blockSize;
            } catch (Exception e) {
                LOG.e(e);
            }
        }
        return totalSpace;
    }

    /**
     * 获取Rom版本
     */
    public static String getRomversion() {
        String rom = "";
        try {
            String modversion = getSystemProperty("ro.modversion");
            String displayId = getSystemProperty("ro.build.display.id");
            if (modversion != null && !modversion.equals("")) {
                rom = modversion;
            }
            if (displayId != null && !displayId.equals("")) {
                rom = displayId;
            }
        } catch (Exception e) {
            LOG.e(e);
        }
        return rom;
    }

    /**
     * 获取系统配置参数
     */
    public static String getSystemProperty(String key) {
        String pValue = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method m = c.getMethod("get", String.class);
            pValue = m.invoke(null, key).toString();
        } catch (Exception e) {
            LOG.e(e);
        }
        return pValue;
    }

    /**
     * 获得android系统sdk版本号
     */
    public static int getOSVersionSDKINT() {
        return android.os.Build.VERSION.SDK_INT;
    }


}
