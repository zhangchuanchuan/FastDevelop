package com.stream.fastdevelop.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.stream.fastdevelop.utils.AppUtils;
import com.stream.fastdevelop.utils.LogUtils;

/**
 * description：自定义application
 * ===============================
 * creator：张川川
 * create time：2016/9/7 10:38
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LogUtils.d("zccTest", "attach base context");
        AppUtils.appContext = base;
    }

    String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Config.initConfig();
        LogUtils.d("zccTest", "application on create");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //只运行在模拟机
        LogUtils.d(TAG, "onTerminate is called");
    }


}
