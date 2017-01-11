package com.stream.fastdevelop.share;

import android.content.Context;

import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.net.NetUtils;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/10 16:21
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class WeiboShare extends SharePlatformBase {
    @Override
    void share(Context context, ShareParams params) {
        IWeiboShareAPI api = ShareConfig.getWeiboShareAPI(context);

    }



}
