package com.stream.fastdevelop.config;

import android.app.Application;

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

    String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Config.initConfig();
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
