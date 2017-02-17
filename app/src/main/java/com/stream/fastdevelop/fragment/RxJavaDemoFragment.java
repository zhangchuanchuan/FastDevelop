package com.stream.fastdevelop.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/18 18:32
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class RxJavaDemoFragment extends CommonFragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Observable.just("test")
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        int i = 2/0;
                        return null;
                    }
                }).map(new Func1<String, Boolean>() {

            @Override
            public Boolean call(String respData) {
                return false;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }
                });

    }
}
