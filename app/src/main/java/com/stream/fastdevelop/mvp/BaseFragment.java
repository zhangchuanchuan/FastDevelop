package com.stream.fastdevelop.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/6 13:58
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public abstract class BaseFragment extends Fragment{

    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  getView(inflater, container, savedInstanceState);
        return view;
    }
    abstract View getView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
