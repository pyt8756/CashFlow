package com.pyt.mylibrary;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.pyt.mylibrary.utils.LOG;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;


@SuppressLint("StaticFieldLeak")
public class BaseApplication extends Application {

    // 获取到主线程的上下文
    protected static Context mContext;
    // 前台进程的Activity
    protected static Activity foregroundActivity;
    // 内存泄露观察
    private static RefWatcher refWatcher;
    //是否debug模式
    private static boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        NeverCrash.init(new NeverCrash.CrashHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                LOG.e("Application", "Oh my app is crash !!!!!!!!!!!!! \n" + Log.getStackTraceString(e));
            }
        });
        initFragmentation();
        initLeakCanary();
        LOG.LOGED = DEBUG;
        initUmeng();
    }

    private void initUmeng() {
        MobclickAgent.setDebugMode(DEBUG);
        MobclickAgent.openActivityDurationTrack(false);// SDK在统计Fragment时，需要关闭Activity自带的页面统计
        MobclickAgent.setScenarioType(mContext, MobclickAgent.EScenarioType.E_UM_NORMAL);//场景类型设置接口。
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        refWatcher = LeakCanary.install(this);
    }

    private void initFragmentation() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(DEBUG ? Fragmentation.BUBBLE : Fragmentation.NONE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(DEBUG)
                // 线上环境时，可能会遇到上述异常，此时debug=false，不会抛出该异常（避免crash），会捕获
                // 建议在回调处上传至我们的Crash检测服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        LOG.e("Application", "Oh Fragmentation is crash !!!!!!!!!!!!! \n" + Log.getStackTraceString(e));
                    }
                })
                .install();
    }

    // 对外暴露上下文
    public static Context getApplication() {
        return mContext;
    }

    public static Context getContext() {
        if (foregroundActivity != null) {
            return foregroundActivity;
        }
        if (mContext != null) {
            return mContext;
        }
        return new Activity();
    }

    // 对外暴露主线程id
    public static int getMainThreadId() {
        return android.os.Process.myTid();
    }

    public static Activity getForegroundActivity() {
        return foregroundActivity;
    }

    public static void setForegroundActivity(Activity foregroundActivity) {
        BaseApplication.foregroundActivity = foregroundActivity;
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDebug(boolean DEBUG) {
        BaseApplication.DEBUG = DEBUG;
    }
}
