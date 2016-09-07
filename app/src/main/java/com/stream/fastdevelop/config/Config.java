package com.stream.fastdevelop.config;

import com.stream.fastdevelop.BuildConfig;
import com.stream.fastdevelop.utils.LogUtils;

/**
 * description：配置类
 * ===============================
 * creator：张川川
 * create time：2016/9/7 17:10
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public class Config {

    // 设置日志等级1, 2, 3, 4, 5, 6, 7， 8
    public static int LOG_LEVEL = 1;

    public static void initConfig(){
        if(!BuildConfig.DEBUG){
            LOG_LEVEL = LogUtils.ASSERT;
        }
    }
}
