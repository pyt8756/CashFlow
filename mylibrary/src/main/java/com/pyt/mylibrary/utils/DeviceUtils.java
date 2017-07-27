package com.pyt.mylibrary.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import static com.pyt.mylibrary.BaseApplication.getApplication;


/**
 * 获取手机信息,  IMEI,IMSI,Number,Sim 状态
 * 权限
 * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 */
public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return android.os.Build.MODEL.trim();
    }

    /**
     * 获取手机厂商
     */
    public static String getDeviceManufacturer() {
        return android.os.Build.MANUFACTURER.trim();
    }

    /**
     * 检测手机是否已插入SIM卡
     */
    public static boolean isCheckSimCardAvailable() {
        final TelephonyManager tm = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSimState() == TelephonyManager.SIM_STATE_READY;
    }

    /**
     * sim卡是否可读
     */
    public static boolean isCanUseSim() {
        try {
            TelephonyManager mgr = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            return TelephonyManager.SIM_STATE_READY == mgr.getSimState();
        } catch (Exception e) {
            LOG.e(e);
        }
        return false;
    }

    /**
     * 返回本地手机号码，这个号码不一定能获取到
     */
    public static String getNativePhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        String NativePhoneNumber = null;
        NativePhoneNumber = telephonyManager.getLine1Number();
        return NativePhoneNumber;
    }


    /**
     * 获取当前设备的SN
     */
    public static String getSimSN() {
        String simSN = null;
        try {
            TelephonyManager tm = (TelephonyManager) getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            simSN = tm.getSimSerialNumber();
        } catch (Exception e) {
            LOG.e(e);
        }
        return simSN;
    }

    /**
     * 获取当前设备的MAC地址
     */
    public static String getMacAddress() {
        String mac = null;
        try {
            WifiManager wm = (WifiManager) getApplication().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wm.getConnectionInfo();
            mac = info.getMacAddress();
        } catch (Exception e) {
            LOG.e(e);
        }
        return mac;
    }

    /**
     * 获得设备ip地址
     */
    public static String getLocalAddress() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            LOG.e(e);
        }
        return null;
    }

    /**
     * 获取设备信息
     */
    public static String[] getDivceInfo() {
        String str1 = "/proc/cpuinfo";
        String str2 = "";
        String[] cpuInfo = {"", ""};
        String[] arrayOfString;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (int i = 2; i < arrayOfString.length; i++) {
                cpuInfo[0] = cpuInfo[0] + arrayOfString[i] + " ";
            }
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            cpuInfo[1] += arrayOfString[2];
            localBufferedReader.close();
        } catch (IOException e) {
            LOG.e(e);
        }
        return cpuInfo;
    }

    /**
     * 返回手机服务商名字
     */
    public static String getProvidersName() {
        String providersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = getIMSI(getApplication());
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            providersName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            providersName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            providersName = "中国电信";
        } else {
            providersName = "其他服务商:" + IMSI;
        }
        return providersName;
    }

    /**
     * IMSI是国际移动用户识别码的简称(International Mobile Subscriber Identity)
     * IMSI共有15位，其结构如下：
     * MCC+MNC+MIN
     * MCC：Mobile Country Code，移动国家码，共3位，中国为460;
     * MNC:Mobile NetworkCode，移动网络码，共2位
     * 在中国，移动的代码为电00和02，联通的代码为01，电信的代码为03
     * 合起来就是（也是Android手机中APN配置文件中的代码）：
     * 中国移动：46000 46002
     * 中国联通：46001
     * 中国电信：46003
     * 举例，一个典型的IMSI号码为460030912121001
     */
    public static String getIMSI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = telephonyManager.getSubscriberId();
        LOG.i(TAG, " IMSI：" + IMSI);
        return IMSI;
    }

    /**
     * IMEI是International Mobile Equipment Identity （国际移动设备标识）的简称
     * IMEI由15位数字组成的”电子串号”，它与每台手机一一对应，而且该码是全世界唯一的
     * 其组成为：
     * 1. 前6位数(TAC)是”型号核准号码”，一般代表机型
     * 2. 接着的2位数(FAC)是”最后装配号”，一般代表产地
     * 3. 之后的6位数(SNR)是”串号”，一般代表生产顺序号
     * 4. 最后1位数(SP)通常是”0″，为检验码，目前暂备用
     */
    public static String getIMEI(Context context) {
        if (AppUtils.getOSVersionSDKINT() < Build.VERSION_CODES.M) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMEI = telephonyManager.getDeviceId();
            LOG.i(TAG, " IMEI：" + IMEI);
            return IMEI;
        }
        return "";
    }


    /**
     * Print telephone info.
     */
    public static String printTelephoneInfo(Context context) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______ 手机信息  ").append(time).append(" ______________");
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMSI = tm.getSubscriberId();
        //IMSI前面三位460是国家号码，其次的两位是运营商代号，00、02是中国移动，01是联通，03是电信。
        String providerName = getProvidersName();
        sb.append(providerName).append("  手机号：").append(tm.getLine1Number()).append(" IMSI是：").append(IMSI);
        sb.append("\nDeviceID(IMEI)       :").append(tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion:").append(tm.getDeviceSoftwareVersion());
        sb.append("\ngetLine1Number       :").append(tm.getLine1Number());
        sb.append("\nNetworkCountryIso    :").append(tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator      :").append(tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName  :").append(tm.getNetworkOperatorName());
        sb.append("\nNetworkType          :").append(tm.getNetworkType());
        sb.append("\nPhoneType            :").append(tm.getPhoneType());
        sb.append("\nSimCountryIso        :").append(tm.getSimCountryIso());
        sb.append("\nSimOperator          :").append(tm.getSimOperator());
        sb.append("\nSimOperatorName      :").append(tm.getSimOperatorName());
        sb.append("\nSimSerialNumber      :").append(tm.getSimSerialNumber());
        sb.append("\ngetSimState          :").append(tm.getSimState());
        sb.append("\nSubscriberId         :").append(tm.getSubscriberId());
        sb.append("\nVoiceMailNumber      :").append(tm.getVoiceMailNumber());

        LOG.i(TAG, sb.toString());
        return sb.toString();
    }

    /////_________________ 双卡双待系统IMEI和IMSI方案（see more on http://benson37.iteye.com/blog/1923946）

    /**
     * 双卡双待神机IMSI、IMSI、PhoneType信息
     * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
     */
    public static class TeleInfo {
        public String imsi_1;
        public String imsi_2;
        public String imei_1;
        public String imei_2;
        public int phoneType_1;
        public int phoneType_2;

        @Override
        public String toString() {
            return "TeleInfo{" +
                    "imsi_1='" + imsi_1 + '\'' +
                    ", imsi_2='" + imsi_2 + '\'' +
                    ", imei_1='" + imei_1 + '\'' +
                    ", imei_2='" + imei_2 + '\'' +
                    ", phoneType_1=" + phoneType_1 +
                    ", phoneType_2=" + phoneType_2 +
                    '}';
        }
    }

    /**
     * MTK Phone.
     * <p>
     * 获取 MTK 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getMtkTeleInfo(Context context) {
        TeleInfo teleInfo = new TeleInfo();
        try {
            Class<?> phone = Class.forName("com.android.internal.telephony.Phone");

            Field fields1 = phone.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            int simId_1 = (Integer) fields1.get(null);

            Field fields2 = phone.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            int simId_2 = (Integer) fields2.get(null);

            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Method getSubscriberIdGemini = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", int.class);
            String imsi_1 = (String) getSubscriberIdGemini.invoke(tm, simId_1);
            String imsi_2 = (String) getSubscriberIdGemini.invoke(tm, simId_2);
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            Method getDeviceIdGemini = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", int.class);
            String imei_1 = (String) getDeviceIdGemini.invoke(tm, simId_1);
            String imei_2 = (String) getDeviceIdGemini.invoke(tm, simId_2);

            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            Method getPhoneTypeGemini = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", int.class);
            int phoneType_1 = (Integer) getPhoneTypeGemini.invoke(tm, simId_1);
            int phoneType_2 = (Integer) getPhoneTypeGemini.invoke(tm, simId_2);
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.i(TAG, "MTK: " + teleInfo);
        return teleInfo;
    }

    /**
     * MTK Phone.
     * <p>
     * 获取 MTK 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getMtkTeleInfo2(Context context) {
        TeleInfo teleInfo = new TeleInfo();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> phone = Class.forName("com.android.internal.telephony.Phone");
            Field fields1 = phone.getField("GEMINI_SIM_1");
            fields1.setAccessible(true);
            int simId_1 = (Integer) fields1.get(null);
            Field fields2 = phone.getField("GEMINI_SIM_2");
            fields2.setAccessible(true);
            int simId_2 = (Integer) fields2.get(null);

            Method getDefault = TelephonyManager.class.getMethod("getDefault", int.class);
            TelephonyManager tm1 = (TelephonyManager) getDefault.invoke(tm, simId_1);
            TelephonyManager tm2 = (TelephonyManager) getDefault.invoke(tm, simId_2);

            String imsi_1 = tm1.getSubscriberId();
            String imsi_2 = tm2.getSubscriberId();
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            String imei_1 = tm1.getDeviceId();
            String imei_2 = tm2.getDeviceId();
            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            int phoneType_1 = tm1.getPhoneType();
            int phoneType_2 = tm2.getPhoneType();
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.i(TAG, "MTK2: " + teleInfo);
        return teleInfo;
    }

    /**
     * Qualcomm Phone.
     * 获取 高通 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getQualcommTeleInfo(Context context) {
        TeleInfo teleInfo = new TeleInfo();
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class<?> simTMclass = Class.forName("android.telephony.MSimTelephonyManager");
            Object sim = context.getSystemService("phone_msim");
            int simId_1 = 0;
            int simId_2 = 1;

            Method getSubscriberId = simTMclass.getMethod("getSubscriberId", int.class);
            String imsi_1 = (String) getSubscriberId.invoke(sim, simId_1);
            String imsi_2 = (String) getSubscriberId.invoke(sim, simId_2);
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imsi_2 = imsi_2;

            Method getDeviceId = simTMclass.getMethod("getDeviceId", int.class);
            String imei_1 = (String) getDeviceId.invoke(sim, simId_1);
            String imei_2 = (String) getDeviceId.invoke(sim, simId_2);
            teleInfo.imei_1 = imei_1;
            teleInfo.imei_2 = imei_2;

            Method getDataState = simTMclass.getMethod("getDataState");
            int phoneType_1 = tm.getDataState();
            int phoneType_2 = (Integer) getDataState.invoke(sim);
            teleInfo.phoneType_1 = phoneType_1;
            teleInfo.phoneType_2 = phoneType_2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.i(TAG, "Qualcomm: " + teleInfo);
        return teleInfo;
    }

    /**
     * Spreadtrum Phone.
     * <p>
     * 获取 展讯 神机的双卡 IMSI、IMSI 信息
     */
    public static TeleInfo getSpreadtrumTeleInfo(Context context) {
        TeleInfo teleInfo = new TeleInfo();
        try {

            TelephonyManager tm1 = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imsi_1 = tm1.getSubscriberId();
            String imei_1 = tm1.getDeviceId();
            int phoneType_1 = tm1.getPhoneType();
            teleInfo.imsi_1 = imsi_1;
            teleInfo.imei_1 = imei_1;
            teleInfo.phoneType_1 = phoneType_1;

            Class<?> phoneFactory = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method getServiceName = phoneFactory.getMethod("getServiceName", String.class, int.class);
            getServiceName.setAccessible(true);
            String spreadTmService = (String) getServiceName.invoke(phoneFactory, Context.TELEPHONY_SERVICE, 1);

            TelephonyManager tm2 = (TelephonyManager) context.getSystemService(spreadTmService);
            String imsi_2 = tm2.getSubscriberId();
            String imei_2 = tm2.getDeviceId();
            int phoneType_2 = tm2.getPhoneType();
            teleInfo.imsi_2 = imsi_2;
            teleInfo.imei_2 = imei_2;
            teleInfo.phoneType_2 = phoneType_2;

        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.i(TAG, "Spreadtrum: " + teleInfo);
        return teleInfo;
    }


    //Ethernet Mac Address
    private static final String ETH0_MAC_ADDRESS = "/sys/class/net/eth0/address";

    /**
     * 获取 Wifi MAC 地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    @Deprecated
    public static String getMacAddress(Context context) {
        return getWifiMacAddress(context);
    }

    /**
     * 获取 Wifi MAC 地址
     * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    public static String getWifiMacAddress(Context context) {
        //wifi mac地址
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        LOG.i(TAG, "WIFI MAC：" + mac);
        return mac;
    }


    /**
     * 获取 以太网 MAC 地址
     */
    public static String getEthernetMacAddress() {
        try {
            String mac = FileUtils.readFileToString(new File(ETH0_MAC_ADDRESS));
            LOG.i(TAG, "Ethernet MAC：" + mac);
            return mac;
        } catch (IOException e) {
            LOG.e(e);
            e.printStackTrace();
            return "unknown";
        }
    }

    /**
     * 获取 ANDROID_ID
     */
    public static String getAndroidId(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        LOG.i(TAG, "ANDROID_ID ：" + androidId);
        return androidId;
    }

    /**
     * 获取 开机时间
     */
    public static String getBootTimeString() {
        long ut = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((ut / 3600));
        int m = (int) ((ut / 60) % 60);
        LOG.i(TAG, h + ":" + m);
        return h + ":" + m;
    }

    public static String printSystemInfo() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date);
        StringBuilder sb = new StringBuilder();
        sb.append("_______  系统信息  ").append(time).append(" ______________");
        sb.append("\nID                 :").append(Build.ID);
        sb.append("\nBRAND              :").append(Build.BRAND);
        sb.append("\nMODEL              :").append(Build.MODEL);
        sb.append("\nRELEASE            :").append(Build.VERSION.RELEASE);
        sb.append("\nSDK                :").append(Build.VERSION.SDK);

        sb.append("\n_______ OTHER _______");
        sb.append("\nBOARD              :").append(Build.BOARD);
        sb.append("\nPRODUCT            :").append(Build.PRODUCT);
        sb.append("\nDEVICE             :").append(Build.DEVICE);
        sb.append("\nFINGERPRINT        :").append(Build.FINGERPRINT);
        sb.append("\nHOST               :").append(Build.HOST);
        sb.append("\nTAGS               :").append(Build.TAGS);
        sb.append("\nTYPE               :").append(Build.TYPE);
        sb.append("\nTIME               :").append(Build.TIME);
        sb.append("\nINCREMENTAL        :").append(Build.VERSION.INCREMENTAL);

        sb.append("\n_______ CUPCAKE-3 _______");
        sb.append("\nDISPLAY            :").append(Build.DISPLAY);

        sb.append("\n_______ DONUT-4 _______");
        sb.append("\nSDK_INT            :").append(Build.VERSION.SDK_INT);
        sb.append("\nMANUFACTURER       :").append(Build.MANUFACTURER);
        sb.append("\nBOOTLOADER         :").append(Build.BOOTLOADER);
        sb.append("\nCPU_ABI            :").append(Build.CPU_ABI);
        sb.append("\nCPU_ABI2           :").append(Build.CPU_ABI2);
        sb.append("\nHARDWARE           :").append(Build.HARDWARE);
        sb.append("\nUNKNOWN            :").append(Build.UNKNOWN);
        sb.append("\nCODENAME           :").append(Build.VERSION.CODENAME);

        sb.append("\n_______ GINGERBREAD-9 _______");
        sb.append("\nSERIAL             :").append(Build.SERIAL);
        LOG.i(TAG, sb.toString());
        return sb.toString();
    }

    /**
     * 检测是否是中兴机器
     */
    public static boolean isZte() {
        return getDeviceModel().toLowerCase().contains("zte");
    }

    /**
     * 判断是否是三星的手机
     */
    public static boolean isSamsung() {
        return getDeviceManufacturer().toLowerCase().contains("samsung");
    }

    /**
     * 检测是否HTC手机
     */
    public static boolean isHTC() {
        return getDeviceManufacturer().toLowerCase().contains("htc");
    }
}
