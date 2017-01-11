package com.stream.fastdevelop.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description：
 * ===============================
 * creator：ZhangChuanchuan
 * create time：2017/1/5 14:12
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */

public class RetrofitClient {
    private static Retrofit mBaseClient;

    public static Retrofit getRetrofitClient(){

        if (mBaseClient == null) {
            synchronized (RetrofitClient.class) {
                if (mBaseClient == null) {
                    mBaseClient = new Retrofit.Builder().baseUrl("https://api.github.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return mBaseClient;
    }
}
