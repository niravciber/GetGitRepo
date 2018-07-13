package com.git.repo.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    public static final String BASE_URL = "https://github.com/";
    public static final String API_URL = "https://api.github.com/";

    public static final String CLIENT_ID = "ed6a9bdba10d478049fd";
    public static final String REDIRECT_URI = "nirav://callback";
    public static final String CLIENT_SECRET = "7b767da9122c055bac487b73cc4d53fb1f93252c";

    public static Retrofit retrofit;
    public static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        return retrofit;
    }

    public static GithubApi getApiRetrofit() {
        GithubApi retrofit = new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GithubApi.class);
        return retrofit;
    }


}
