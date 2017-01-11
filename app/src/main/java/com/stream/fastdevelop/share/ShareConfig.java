package com.stream.fastdevelop.share;

import android.content.Context;

import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/10 17:00
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class ShareConfig {
    /**
     * App Key：3530547101
     * App Secret：3673118b8b96fe0cdeaba49ca79ce6f9
     */
    public final static String WEIBO_KEY = "3530547101";
    public final static String WEIBO_SECRET = "3673118b8b96fe0cdeaba49ca79ce6f9";


    private static IWeiboShareAPI weiboShareAPI;

    public static IWeiboShareAPI getWeiboShareAPI(Context context) {
        if (weiboShareAPI == null) {
            synchronized (ShareConfig.class) {
                if (weiboShareAPI == null) {
                    weiboShareAPI = WeiboShareSDK.createWeiboAPI(context, ShareConfig.WEIBO_KEY);
                }
            }
        }
        return weiboShareAPI;
    }
}
