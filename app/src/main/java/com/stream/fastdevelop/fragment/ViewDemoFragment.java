package com.stream.fastdevelop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.fastdevelop.R;

/**
 * Created by 张川川 on 2016/8/21.
 * 一些自定义View的Demo
 */
public class ViewDemoFragment extends CommonFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_demo, container, false);
    }
}
