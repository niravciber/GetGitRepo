package com.git.repo.di.module;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.git.repo.di.ViewModelModule;
import com.git.repo.network.GithubApi;
import com.git.repo.view.RepoAdapter;

import javax.inject.Named;
import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class Appmodule {

    private static final String BASE_URL = "https://github.com/";
    private static final String API_URL = "https://api.github.com/";

    @Provides
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Provides
    @Singleton
    @Named("base_url")
    public GithubApi provideGitApi(
            @Named("base_url")
                    Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }


    @Singleton
    @Provides
    @Named("base_url")
    Retrofit providesRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }


    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }


    @Provides
    @Singleton
    @Named("api_url")
    public GithubApi provideGithubApi(@Named("api_url") Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }


    @Singleton
    @Provides
    @Named("api_url")
    Retrofit providesRetrofitA(GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }


    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdaperFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
