package com.git.repo.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.git.repo.model.LoginResponse;
import com.git.repo.model.PreferenceManager;
import com.git.repo.network.GithubApi;
import com.git.repo.network.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel{

    MutableLiveData<Boolean> webLivedata = new MutableLiveData<>();
    MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    public void onButtonClick(View view) {
            webLivedata.setValue(true);
    }

    public LiveData<Boolean> getWebLivedata() {
        return webLivedata;
    }

    public LiveData<LoginResponse> getLoginResponseLivedata(){
        return loginResponseMutableLiveData;
    }
    public void getAccessToken(String code){
        GithubApi githubApi = RetrofitClient.getRetrofit().create(GithubApi.class);

        Call<LoginResponse> call = githubApi.getAccessToken(RetrofitClient.CLIENT_ID,code,RetrofitClient.CLIENT_SECRET);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("response",response.toString());
                if(response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    loginResponseMutableLiveData.setValue(loginResponse);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("response","error");
            }
        });
    }
}
