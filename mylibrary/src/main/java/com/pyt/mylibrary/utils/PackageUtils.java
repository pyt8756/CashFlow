package com.pyt.mylibrary.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * 包工具
 */
public class PackageUtils {

    /**
     * 根据packageName获取packageInfo，传null 获取当前应用的信息
     */
    public static PackageInfo getPackageInfo(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            packageName = getApplication().getPackageName();
        }
        PackageInfo info = null;
        try {
            info = getApplication().getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e(e);
        }
        return info;
    }

    /**
     * 获取指定程序信息
     */
    public static ApplicationInfo getApplicationInfo(Context context, String pkg) {
        try {
            return context.getPackageManager().getApplicationInfo(pkg, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否是第三方软件
     */
    public static boolean isThirdPartyApp(String packageName) {
        PackageManager pm = getApplication().getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = pm.getPackageInfo(packageName, 0);
            return isThirdPartyApp(packageInfo);
        } catch (PackageManager.NameNotFoundException e) {
            LOG.e(e);
            return false;
        }
    }

    /**
     * 判断是否是第三方软件
     */
    public static boolean isThirdPartyApp(PackageInfo packageInfo) {
        return !(null == packageInfo || null == packageInfo.applicationInfo)
                && (((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) ||
                ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0));
    }

    /**
     * 读取指定路径下APK文件签名
     */
    public static String getJarSignature(String filePath) throws Exception {
        if (null == filePath) {
            return null;
        }
        String resultSign = "";
        String resultKey = "";
        List<ZipEntry> names = new ArrayList<>();
        ZipFile zf = new ZipFile(filePath);
        Enumeration<ZipEntry> zi = (Enumeration<ZipEntry>) zf.entries();
        while (zi.hasMoreElements()) {
            ZipEntry ze = zi.nextElement();
            String name = ze.getName();
            if (name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA"))) {
                names.add(ze);
            }
        }
        Collections.sort(names, new Comparator<ZipEntry>() {
            @Override
            public int compare(ZipEntry obj1, ZipEntry obj2) {
                if (obj1 != null && obj2 != null) {
                    return obj1.getName().compareToIgnoreCase(obj2.getName());
                }
                return 0;
            }
        });
        for (ZipEntry ze : names) {
            InputStream is = zf.getInputStream(ze);
            try {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                CertPath cp = cf.generateCertPath(is, "PKCS7");
                List<?> list = cp.getCertificates();
                for (Object obj : list) {
                    if (!(obj instanceof X509Certificate)) continue;
                    X509Certificate cert = (X509Certificate) obj;
                    StringBuilder builder = new StringBuilder();
                    builder.setLength(0);
                    byte[] key = getPKBytes(cert.getPublicKey());
                    for (byte aKey : key) {
                        builder.append(String.format("%02X", aKey));
                    }
                    resultKey += builder.toString();
                    builder.setLength(0);
                    byte[] signature = cert.getSignature();

                    for (byte aSignature : signature) {
                        builder.append(String.format("%02X", aSignature));
                    }
                    resultSign += builder.toString();
                }
            } catch (CertificateException e) {
                LOG.e(e);
            }
            is.close();
        }
        if (!StringUtils.isEmpty(resultKey) && !TextUtils.isEmpty(resultSign)) {
            return hashCode(resultKey) + "," + hashCode(resultSign);
        }
        return null;
    }

    /**
     * 根据公钥获取key
     */
    private static byte[] getPKBytes(PublicKey pk) {
        if (pk instanceof RSAPublicKey) {
            RSAPublicKey k = (RSAPublicKey) pk;
            return k.getModulus().toByteArray();
        } else if (pk instanceof DSAPublicKey) {
            DSAPublicKey k = (DSAPublicKey) pk;
            return k.getY().toByteArray();
        }
        return null;
    }

    /**
     * 计算签名时的hashcode算法
     */
    public static int hashCode(String str) {
        int hash = 0;
        if (str != null) {
            int multiplier = 1;
            int offset = 0;
            int count = str.length();
            char[] value = new char[count];
            str.getChars(offset, count, value, 0);
            for (int i = offset + count - 1; i >= offset; i--) {
                hash += value[i] * multiplier;
                int shifted = multiplier << 5;
                multiplier = shifted - multiplier;
            }
        }
        return hash;
    }

    /**
     * 通过包名读取已安装APP数字签名
     */
    public static String getInstalledPackageSignature(String packageName) {
        String signature = null;
        try {
            PackageManager pm = getApplication().getPackageManager();
            ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
            String apkPath = appInfo.sourceDir;
            signature = getJarSignature(apkPath);
        } catch (Exception e) {
            LOG.e(e);
        }
        return signature;
    }

    /**
     * 获取指定路径的apk的资源
     */
    public static Resources getAPKResources(String apkPath) throws Exception {
        String PathAssetManager = "android.content.res.AssetManager";
        Class assetMagCls = Class.forName(PathAssetManager);
        Constructor assetMagCt = assetMagCls.getConstructor((Class[]) null);
        Object assetMag = assetMagCt.newInstance((Object[]) null);
        Class[] typeArgs = new Class[1];
        typeArgs[0] = String.class;
        Method assetMagAddAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath", typeArgs);
        Object[] valueArgs = new Object[1];
        valueArgs[0] = apkPath;
        assetMagAddAssetPathMtd.invoke(assetMag, valueArgs);
        Resources res = getApplication().getResources();
        typeArgs = new Class[3];
        typeArgs[0] = assetMag.getClass();
        typeArgs[1] = res.getDisplayMetrics().getClass();
        typeArgs[2] = res.getConfiguration().getClass();
        Constructor resCt = Resources.class.getConstructor(typeArgs);
        valueArgs = new Object[3];
        valueArgs[0] = assetMag;
        valueArgs[1] = res.getDisplayMetrics();
        valueArgs[2] = res.getConfiguration();
        res = (Resources) resCt.newInstance(valueArgs);
        return res;
    }

    /**
     * 是否系统应用程序
     */
    public static boolean isSystemApplication(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        if (StringUtils.isEmpty(packageName)) {
            packageName = context.getPackageName();
        }
        if (packageManager == null || packageName == null || packageName.length() == 0) {
            return false;
        }
        try {
            ApplicationInfo app = packageManager.getApplicationInfo(packageName, 0);
            return (app != null && (app.flags & ApplicationInfo.FLAG_SYSTEM) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 调用系统安装应用
     */
    public static boolean install(Context context, File file) {
        if (file == null || !file.exists() || !file.isFile()) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return true;
    }

    /**
     * 调用系统卸载应用
     */
    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        Uri packageURI = Uri.parse("package:" + packageName);
        intent.setData(packageURI);
        context.startActivity(intent);
    }

    /**
     * 启动应用
     */
    public static boolean startAppByPackageName(Context context, String packageName) {
        return startAppByPackageName(context, packageName, null);
    }

    /**
     * 启动应用
     */
    public static boolean startAppByPackageName(Context context, String packageName, Map<String, String> param) {
        PackageInfo pi;
        try {
            pi = context.getPackageManager().getPackageInfo(packageName, 0);
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(pi.packageName);
            List<ResolveInfo> apps = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
            ResolveInfo ri = apps.iterator().next();
            if (ri != null) {
                String packageName1 = ri.activityInfo.packageName;
                String className = ri.activityInfo.name;
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                ComponentName cn = new ComponentName(packageName1, className);
                intent.setComponent(cn);
                if (param != null) {
                    for (Map.Entry<String, String> en : param.entrySet()) {
                        intent.putExtra(en.getKey(), en.getValue());
                    }
                }
                context.startActivity(intent);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context.getApplicationContext(), "启动失败",
                    Toast.LENGTH_LONG).show();
        }
        return false;
    }

    /**
     * 打开已安装应用的详情
     */
    public static void showInstalledAppDetails(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", packageName, null));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取指定程序信息
     */
    public static ActivityManager.RunningTaskInfo getTopRunningTask(Context context) {
        try {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            // 得到当前正在运行的任务栈
            List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(1);
            // 得到前台显示的任务栈
            ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
            return runningTaskInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取已安装的全部应用信息
     */
    public static List<PackageInfo> getInsatalledPackages(Context context) {
        return context.getPackageManager().getInstalledPackages(0);
    }

    /**
     * 获取已安装的全部应用信息
     */
    public static boolean isInsatalled(Context context, String pkg) {
        if (!StringUtils.isEmpty(pkg)) {
            List<PackageInfo> list = getInsatalledPackages(context);
            if (list != null) {
                for (PackageInfo pi : list) {
                    if (pkg.equalsIgnoreCase(pi.packageName)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
