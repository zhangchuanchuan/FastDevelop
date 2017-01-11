package com.stream.fastdevelop.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stream.fastdevelop.retrofit.GithubService;
import com.stream.fastdevelop.retrofit.Repo;
import com.stream.fastdevelop.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        GithubService service = RetrofitClient.getRetrofitClient()
                .create(GithubService.class);
        Call<List<Repo>> call = service.listRepos("zhangchuanchuan");

        //异步请求
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d("zcc", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

//        Observable.create(new Observable.OnSubscribe(){
//            @Override
//            public void call(Object o) {
//                throw new RuntimeException();
//            }
//        });


    }
}
