package com.pyt.mylibrary.utils;

import android.os.Environment;

import java.io.File;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * 本应用数据清除管理器
 */
public class CacheUtils {

    public static String getTotalCacheSize() {
        long cacheSize = 0;
        try {
            cacheSize = getFolderSize(getApplication().getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                cacheSize += getFolderSize(getApplication().getExternalCacheDir());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FileUtils.convertFileSize(cacheSize);
    }

    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                // 如果下面还有文件
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     */
    public static void cleanInternalCache() {
        deleteFilesByDirectory(getApplication().getCacheDir());
    }

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     */
    public static void cleanDatabases() {
        deleteFilesByDirectory(new File("/data/data/"
                + getApplication().getPackageName() + "/databases"));
    }

    /**
     * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     */
    public static void cleanSharedPreference() {
        deleteFilesByDirectory(new File("/data/data/"
                + getApplication().getPackageName() + "/shared_prefs"));
    }

    /**
     * 按名字清除本应用数据库
     */
    public static void cleanDatabaseByName(String dbName) {
        getApplication().deleteDatabase(dbName);
    }

    /**
     * 清除/data/data/com.xxx.xxx/files下的内容
     */
    public static void cleanFiles() {
        deleteFilesByDirectory(getApplication().getFilesDir());
    }

    /**
     * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     */
    public static void cleanExternalCache() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(getApplication().getExternalCacheDir());
        }
    }

    /**
     * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * 清除本应用所有的数据
     */
    public static void cleanApplicationData(String... filepath) {
        cleanInternalCache();
        cleanExternalCache();
        cleanDatabases();
        cleanSharedPreference();
        cleanFiles();
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }

    private static boolean deleteFilesByDirectory(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteFilesByDirectory(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir != null && dir.delete();
    }
}
