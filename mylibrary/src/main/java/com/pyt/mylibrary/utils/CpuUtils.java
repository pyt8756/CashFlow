package com.pyt.mylibrary.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;


/**
 * cpu工具
 */
public class CpuUtils {

    private static final String TAG = CpuUtils.class.getSimpleName();
    private static final String CPU_INFO_PATH = "/proc/cpuinfo";
    private static final String CMD_CAT = "/system/bin/cat";
    private static final String CPU_FREQ_CUR_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq";
    private static final String CPU_FREQ_MAX_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq";
    private static final String CPU_FREQ_MIN_PATH = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq";

    private static String CPU_NAME;
    private static long CPU_MAX_FREQENCY = 0;
    private static long CPU_MIN_FREQENCY = 0;

    /**
     * 打印cpu信息
     */
    public static String printCpuInfo() {
        String info = FileUtils.getFileOutputString(CPU_INFO_PATH);
        LOG.i(TAG, "_______  CPU :   \n" + info);
        return info;
    }

    /**
     * 判断手机CPU是否支持NEON指令集
     */
    public static boolean isNEON() {
        boolean isNEON = false;
        String cupinfo = getCPUInfos();
        if (cupinfo != null) {
            cupinfo = cupinfo.toLowerCase();
            isNEON = cupinfo.contains("neon");
        }
        return isNEON;
    }

    /**
     * 读取CPU信息文件，获取CPU信息
     */
    private static String getCPUInfos() {
        String str1 = "/proc/cpuinfo";
        String str2;
        StringBuilder resusl = new StringBuilder();
        String resualStr;
        try {
            FileReader fr = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(fr, 8192);
            while ((str2 = localBufferedReader.readLine()) != null) {
                resusl.append(str2);
            }
            resualStr = resusl.toString();
            return resualStr;
        } catch (IOException e) {
            LOG.e(e);
        }
        return null;
    }

    /**
     * 获取CPU核心数
     */
    public static int getCpuCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获取CPU的名字
     */
    public static String getCpuName() {
        if (!StringUtils.isEmpty(CPU_NAME)) {
            return CPU_NAME;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CPU_INFO_PATH), 8192);
            String line = bufferedReader.readLine();
            bufferedReader.close();
            String[] array = line.split(":\\s+", 2);
            if (array.length > 1) {
                LOG.i(TAG, array[1]);
                CPU_NAME = array[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CPU_NAME;
    }

    /**
     * 得到CPU当前频率
     */
    public static long getCurrentFreqency() {
        try {
            return Long.parseLong(FileUtils.getFileOutputString(CPU_FREQ_CUR_PATH).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到CPU最大频率
     */
    public static long getMaxFreqency() {
        if (CPU_MAX_FREQENCY > 0) {
            return CPU_MAX_FREQENCY;
        }
        try {
            CPU_MAX_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MAX_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MAX_FREQENCY;
    }

    /**
     * 得到CPU最小频率
     */
    public static long getMinFreqency() {
        if (CPU_MIN_FREQENCY > 0) {
            return CPU_MIN_FREQENCY;
        }
        try {
            CPU_MIN_FREQENCY = Long.parseLong(getCMDOutputString(new String[]{CMD_CAT, CPU_FREQ_MIN_PATH}).trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CPU_MIN_FREQENCY;
    }

    private static String getCMDOutputString(String[] args) {
        try {
            ProcessBuilder cmd = new ProcessBuilder(args);
            Process process = cmd.start();
            InputStream in = process.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] re = new byte[64];
            int len;
            while ((len = in.read(re)) != -1) {
                sb.append(new String(re, 0, len));
            }
            in.close();
            process.destroy();
            LOG.i(TAG, "CMD: " + sb.toString());
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }
}
