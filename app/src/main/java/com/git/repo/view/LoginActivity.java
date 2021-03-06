package com.git.repo.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.git.repo.R;
import com.git.repo.databinding.ActivityLoginBinding;
import com.git.repo.model.LoginResponse;
import com.git.repo.model.PreferenceHelper;
import com.git.repo.network.RetrofitClient;
import com.git.repo.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class LoginActivity extends DaggerAppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Inject
    PreferenceHelper preferenceHelper;
    @Inject
    ViewModelProvider.Factory viewmodelFactory;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this,viewmodelFactory).get(LoginViewModel.class);
        binding.setListner(loginViewModel);
        loginViewModel.getWebLivedata().observe(this,observer);
        loginViewModel.getLoginResponseLivedata().observe(this,loginResponseObserver);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Uri uri = getIntent().getData();
        if(uri!=null && uri.toString().startsWith(RetrofitClient.REDIRECT_URI)) {
            String code = uri.getQueryParameter("code");
            Toast.makeText(this,"code gotcha"+code,Toast.LENGTH_SHORT).show();
            preferenceHelper.set(PreferenceHelper.PREF_CODE,code);
            loginViewModel.getAccessToken(code);
        }
        getIntent().setData(null);
    }

    Observer<Boolean> observer = aBoolean -> {
         String url = "https://github.com/login/oauth/authorize?client_id=ed6a9bdba10d478049fd&redirect_uri="+ RetrofitClient.REDIRECT_URI;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivityForResult(browserIntent,101);
    };

    Observer<LoginResponse> loginResponseObserver = loginResponse -> {
        preferenceHelper.set(PreferenceHelper.PREF_ACCESS_TOKEN,loginResponse.access_token);
        preferenceHelper.set(PreferenceHelper.PREF_ACCESS_TOKEN_TYPE,loginResponse.token_type);
        startActivity(new Intent(this,MainActivity.class));
    };
}

