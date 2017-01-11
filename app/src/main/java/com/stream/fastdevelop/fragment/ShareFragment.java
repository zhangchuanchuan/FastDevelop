package com.stream.fastdevelop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.fastdevelop.R;
import com.stream.fastdevelop.activity.WeiboShareActivity;
import com.stream.fastdevelop.share.ShareParams;
import com.stream.fastdevelop.share.WeiboShare;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/10 16:08
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class ShareFragment extends CommonFragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.weixin).setOnClickListener(this);
        view.findViewById(R.id.weixinQ).setOnClickListener(this);
        view.findViewById(R.id.qq).setOnClickListener(this);
        view.findViewById(R.id.qqQ).setOnClickListener(this);
        view.findViewById(R.id.weibo).setOnClickListener(this);
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
                Intent intent = new Intent();
                intent.setClass(getActivity(), WeiboShareActivity.class);
                startActivity(intent);
                break;
        }
    }
}
