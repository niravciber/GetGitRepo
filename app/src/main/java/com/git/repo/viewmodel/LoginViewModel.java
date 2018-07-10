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
    public void onButtonClick(View view) {
            webLivedata.setValue(true);
    }

    public LiveData<Boolean> getWebLivedata() {
        return webLivedata;
    }

    public void getAccessToken(String code){

    }
}
