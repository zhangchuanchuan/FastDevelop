package com.stream.fastdevelop.utils;


import android.util.Log;
import android.view.WindowManager;

import com.stream.fastdevelop.config.Config;

/**
 * description：日志工具类
 * ===============================
 * creator：张川川
 * create time：2016/9/7 16:52
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class LogUtils {


    private LogUtils() {
    }

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    public static int v(String tag, String msg) {
        if(!canPrintLogV()){
            return 0;
        }
        return Log.v(tag, msg);
    }

    public static int d(String tag, String msg) {
        if(!canPrintLogD()){
            return 0;
        }
        return Log.d(tag, msg);
    }

    public static int i(String tag, String msg) {
        if(!canPrintLogI()){
            return 0;
        }
        return Log.i(tag, msg);
    }

    public static int w(String tag, String msg) {
        if(!canPrintLogW()){
            return 0;
        }
        return Log.w(tag, msg);
    }

    public static int e(String tag, String msg) {
        if(!canPrintLogE()){
            return 0;
        }
        return Log.e(tag, msg);
    }




    private static boolean canPrintLogV() {
        return Config.LOG_LEVEL <= VERBOSE;
    }

    private static boolean canPrintLogD() {
        return Config.LOG_LEVEL <= DEBUG;
    }

    private static boolean canPrintLogI() {
        return Config.LOG_LEVEL <= INFO;
    }

    private static boolean canPrintLogW() {
        return Config.LOG_LEVEL <= WARN;
    }

    private static boolean canPrintLogE() {
        return Config.LOG_LEVEL <= ERROR;
    }

//    private static boolean canPrintLogA() {
//        return Config.LOG_LEVEL <= ASSERT;
//    }
}
