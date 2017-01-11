package com.stream.fastdevelop.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sina.weibo.sdk.api.BaseMediaObject;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.ProvideMultiMessageForWeiboResponse;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.stream.fastdevelop.R;
import com.stream.fastdevelop.share.ShareConfig;
import com.stream.fastdevelop.share.ShareParams;
import com.stream.fastdevelop.share.StatusesAPI;
import com.stream.fastdevelop.share.WeiboShare;
import com.stream.fastdevelop.utils.SharedPreferencesUtils;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/11 12:05
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class WeiboShareActivity extends BaseActivity implements IWeiboHandler.Response, View.OnClickListener{

    /** 微博微博分享接口实例 */
    private IWeiboShareAPI mShareWeiboAPI    = null;
    /** 从微博客户端唤起第三方应用时，客户端发送过来的请求数据对象 */
    private BaseRequest mBaseRequest = null;


    /** 当前 Token 信息 */
    private Oauth2AccessToken mAccessToken = Oauth2AccessToken.parseAccessToken(SharedPreferencesUtils.getString("accessToken", ""));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_share);
        findViewById(R.id.weixin).setOnClickListener(this);
        findViewById(R.id.weixinQ).setOnClickListener(this);
        findViewById(R.id.qq).setOnClickListener(this);
        findViewById(R.id.qqQ).setOnClickListener(this);
        findViewById(R.id.weibo).setOnClickListener(this);
        //注册
        mShareWeiboAPI = ShareConfig.getWeiboShareAPI(this);
        mShareWeiboAPI.registerApp();


        mAuthInfo = new AuthInfo(this, ShareConfig.WEIBO_KEY, "http://www.weibo.com", "");
        mSsoHandler = new SsoHandler(this, mAuthInfo);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mShareWeiboAPI.handleWeiboResponse(intent, this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weixin:
                break;
            case R.id.weixinQ:
                break;
            case R.id.qq:
                break;
            case R.id.qqQ:
                break;
            case R.id.weibo:

                openShare();
                break;
        }
    }


    AuthInfo mAuthInfo;
    SsoHandler mSsoHandler;

    /**
     * 一键分享
     */
    public void openShare() {
        if (mAccessToken != null && mAccessToken.isSessionValid()) {
            doShare();
        } else {
            // 跳转授权并分享
            mSsoHandler.authorizeWeb(new WeiboAuthListener() {
                @Override
                public void onComplete(Bundle bundle) {
                    mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);
                    String access = getAccessJson(bundle);
                    SharedPreferencesUtils.putString("accessToken", access);
                    doShare();
                }

                @Override
                public void onWeiboException(WeiboException e) {
                    Toast.makeText(WeiboShareActivity.this, "错误", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(WeiboShareActivity.this, "取消", Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            Toast.makeText(WeiboShareActivity.this, "分享成功", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(WeiboShareActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void doShare() {
        /* 用于获取微博信息流等操作的API */
        StatusesAPI mStatusesAPI = new StatusesAPI(getApplicationContext(), ShareConfig.WEIBO_KEY, mAccessToken);
//        mStatusesAPI.update("发送一条纯文字微博", null, null, mListener);
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
                mStatusesAPI.upload("发送一条带本地图片的微博http://www.baidu.com", bitmap, null, null, mListener);

        // 请注意：该接口暂不支持发布多图，即参数pic_id不可用（只能通过BD合作接入，不对个人申请）
//                mStatusesAPI.uploadUrlText("发送一条带网络图片的微博", photoURL, null, null, null, mListener);

    }

    public void testShareImage() {
        WeiboMessage weiboMessage = new WeiboMessage();
        ImageObject imageObject = new ImageObject();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        imageObject.setImageObject(bitmap);
        weiboMessage.mediaObject = imageObject;
        SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.message = weiboMessage;
        mShareWeiboAPI.sendRequest(WeiboShareActivity.this, request);
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        if (baseResponse != null) {
            switch (baseResponse.errCode) {
                case WBConstants.ErrorCode.ERR_OK:
                    Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_CANCEL:
                    Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
                    break;
                case WBConstants.ErrorCode.ERR_FAIL:
                    Toast.makeText(this, "Error Message: " + baseResponse.errMsg,
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }


    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMessage(boolean hasText, boolean hasImage,
                             boolean hasWebpage) {
        sendMultiMessage(hasText, hasImage, hasWebpage);
    }

    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     * 注意：当 {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条消息，
     * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
     *
     * @param hasText    分享的内容是否有文本
     * @param hasImage   分享的内容是否有图片
     * @param hasWebpage 分享的内容是否有网页
     */
    private void sendMultiMessage(boolean hasText, boolean hasImage, boolean hasWebpage) {

        // 1. 初始化微博的分享消息
        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        if (hasText) {
            weiboMessage.textObject = getTextObj();
        }

        if (hasImage) {
            weiboMessage.imageObject = getImageObj();
        }

        // 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
        if (hasWebpage) {
            weiboMessage.mediaObject = getWebpageObj();
        }

        // 2. 初始化从第三方到微博的消息请求
        SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
        // 用transaction唯一标识一个请求
        request.transaction = String.valueOf(System.currentTimeMillis());
        request.multiMessage = weiboMessage;

        // 3. 发送请求消息到微博，唤起微博分享界面
        String SCOPE = // 应用申请的 高级权限
                "email,direct_messages_read,direct_messages_write,"
                        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                        + "follow_app_official_microblog," + "invitation_write";
            AuthInfo authInfo = new AuthInfo(this, ShareConfig.WEIBO_KEY, "http://www.sina.com", SCOPE);
            Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(SharedPreferencesUtils.getString("accessToken", ""));
            String token = "";
            if (accessToken != null) {
                token = accessToken.getToken();
            }
            mShareWeiboAPI.sendRequest(this, request, authInfo, token, new WeiboAuthListener() {
                @Override
                public void onWeiboException( WeiboException arg0 ) {
                }

                @Override
                public void onComplete( Bundle bundle ) {
                    // TODO Auto-generated method stub
                    Oauth2AccessToken newToken = Oauth2AccessToken.parseAccessToken(bundle);
                    String access = getAccessJson(bundle);
                    SharedPreferencesUtils.putString("accessToken", access);

                    Toast.makeText(getApplicationContext(), "onAuthorizeComplete token = " + newToken.getToken(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancel() {
                }
            });
        }

    public TextObject getTextObj() {
        TextObject obj =  new TextObject();
        obj.text = "haha";
        return obj;
    }

    public ImageObject getImageObj() {
        return new ImageObject();
    }

    public BaseMediaObject getWebpageObj() {
        return new BaseMediaObject() {
            @Override
            public int getObjType() {
                return 0;
            }

            @Override
            protected BaseMediaObject toExtraMediaObject(String s) {
                return null;
            }

            @Override
            protected String toExtraMediaString() {
                return null;
            }
        };
    }

    public String getAccessJson(Bundle bundle) {
        return  "{" +
                "\"uid\":" + "\"" + getString(bundle, "uid", "") +"\""+ ";" +
                "access_token:" +"\""+ getString(bundle, "access_token", "") +"\""+ ";" +
                "expires_in:" +"\""+ getString(bundle, "expires_in", "") +"\""+ ";" +
                "refresh_token:" +"\""+ getString(bundle, "refresh_token", "") +"\""+ ";" +
                "phone_num:" +"\""+ getString(bundle, "phone_num", "")+"\"" +
                "}";
    }

    private String getString(Bundle bundle, String key, String defaultValue) {
        if(bundle != null) {
            String value = bundle.getString(key);
            return value != null?value:defaultValue;
        } else {
            return defaultValue;
        }
    }
}
