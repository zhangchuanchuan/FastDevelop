package com.stream.fastdevelop.share;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.ProvideMessageForWeiboRequest;
import com.sina.weibo.sdk.api.share.ProvideMultiMessageForWeiboResponse;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
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

public class WeiboShare {
    public void share(Activity activity, ShareParams params, IWeiboShareAPI api, BaseRequest baseRequest) {

        TextObject textObject = null;
        ImageObject imageObject = null;
        WebpageObject webpageObject = null;
        if (!TextUtils.isEmpty(params.getShareContent())) {
            textObject = new TextObject();
            textObject.text = params.getShareContent();
        }

        if (!TextUtils.isEmpty(params.getShareImage())) {
            imageObject = new ImageObject();
            imageObject.imagePath = params.getShareImage();
        }
//
        if (!TextUtils.isEmpty(params.getShareUrl())) {
            webpageObject = new WebpageObject();
            webpageObject.actionUrl = params.getShareUrl();
        }

        if (api.isWeiboAppSupportAPI()) {
            int supportApi = api.getWeiboAppSupportAPI();
            if (supportApi >= 10351 /*ApiUtils.BUILD_INT_VER_2_2*/) {
                WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
                weiboMultiMessage.imageObject = imageObject;
                weiboMultiMessage.textObject = textObject;
                weiboMultiMessage.mediaObject = webpageObject;

                SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
                request.transaction = String.valueOf(System.currentTimeMillis());
                request.multiMessage = weiboMultiMessage;
                api.sendRequest(activity, request);
            } else {

            }
        } else {
            Toast.makeText(activity, "位置错误", Toast.LENGTH_SHORT).show();
        }





    }

    /**
     * 检查权限
     */
    private boolean checkAuth() {
        return false;
    }

    /**
     * 分享操作
     */
    private boolean doShare() {
        return false;
    }





}
