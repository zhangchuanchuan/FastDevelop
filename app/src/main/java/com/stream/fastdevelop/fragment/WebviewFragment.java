package com.stream.fastdevelop.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.activity.CommonActivity;
import com.stream.fastdevelop.activity.WeiboShareActivity;
import com.stream.fastdevelop.mvp.BaseFragment;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/17 14:59
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class WebviewFragment extends CommonFragment {

    private WebView webView;

    private String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        init(view);
        initData();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (getActivity() != null) {
            Bundle bundle = getActivity().getIntent().getExtras();
            url = bundle.getString("url");
            loadUrl(url);
        }
    }

    private void loadUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url);
        }
    }

    /**
     * 初始化View
     * @param view view
     */
    private void init(View view) {
        webView = (WebView) view.findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(false);

        webView.setWebViewClient(new MyWebViewClinet());

    }


    /**
     *  跳转到web view
     * @param context 启动activity的context
     * @param url 加载url
     */
    public static void jumpWebviewFragment(Context context, String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent();
        intent.setClass(context, CommonActivity.class);
        intent.putExtra("fragment", WebviewFragment.class.getName());
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    class MyWebViewClinet extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            WeiboShareActivity.mLinstener.onOverrideUrl(url);
            getActivity().finish();
            return true;
        }
    }
    public interface WebviewResponseLinstener {
        void onOverrideUrl(String url);

        void onCancel();
    }
}


