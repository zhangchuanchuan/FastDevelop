package com.stream.fastdevelop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rx.Observable;
import rx.Subscriber;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/4 12:31
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class RetrofitFragment extends CommonFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.create(new Observable.OnSubscribe(){
            @Override
            public void call(Object o) {
                throw new RuntimeException();
            }
        });


    }
}
