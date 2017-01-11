package com.stream.fastdevelop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/11 14:33
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class SharedPreferencesUtils {

    private static SharedPreferences sharedPreferences = AppUtils.appContext.getSharedPreferences("fastdevelop", Context.MODE_PRIVATE);

    public static boolean putString(String valule, String key) {
        return sharedPreferences.edit().putString(valule, key).commit();
    }

    public static String getString(String key, String def) {
        return sharedPreferences.getString(key, def);
    }

}
