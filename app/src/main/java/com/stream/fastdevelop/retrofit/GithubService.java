package com.stream.fastdevelop.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * description：
 * ===============================
 * creator：58
 * create time：2016/8/15 11:56
 * ===============================
 * reasons for modification：
 * Modifier：
 * Modify time：
 */
public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    //TODO 添加各种各样的API到这个类
}
