package com.git.repo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.git.repo.model.LoginResponse;
import com.git.repo.model.PreferenceHelper;
import com.git.repo.network.GithubApi;
import com.git.repo.network.RetrofitClient;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel extends ViewModel {


    @Inject@Named("base_url")
    GithubApi githubApi;

    @Inject
    PreferenceHelper preferenceHelper;

    @Inject
    LoginViewModel(){}


    private MutableLiveData<Boolean> webLivedata = new MutableLiveData<>();
    private MutableLiveData<LoginResponse> loginResponseMutableLiveData = new MutableLiveData<>();
    public void onButtonClick(View view) {
        webLivedata.setValue(true);
    }

    public LiveData<Boolean> getWebLivedata() {
        return webLivedata;
    }

    public LiveData<LoginResponse> getLoginResponseLivedata() {
        return loginResponseMutableLiveData;
    }

    public void getAccessToken(String code) {

        Call<LoginResponse> call = githubApi.getAccessToken(RetrofitClient.CLIENT_ID, code, RetrofitClient.CLIENT_SECRET);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("response", response.toString());
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    loginResponseMutableLiveData.setValue(loginResponse);
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("response", "error");
            }
        });
    }
}
