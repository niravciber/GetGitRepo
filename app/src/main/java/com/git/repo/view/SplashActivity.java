package com.git.repo.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.git.repo.R;
import com.git.repo.databinding.ActivitySplashBinding;
import com.git.repo.model.PreferenceHelper;
import com.git.repo.viewmodel.SplashViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class SplashActivity extends DaggerAppCompatActivity {


    private ActivitySplashBinding binding ;
    private SplashViewModel splashViewmodel;
    @Inject
    PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashViewmodel = ViewModelProviders.of(this).get(SplashViewModel.class);
        splashViewmodel.getisLoading().observe(this,observer);
        splashViewmodel.startHandler();

    }


    final android.arch.lifecycle.Observer<Boolean> observer = aBoolean -> {
        if(aBoolean){
            String access_token = preferenceHelper.getStringPrefs( PreferenceHelper.PREF_ACCESS_TOKEN);
            if(TextUtils.isEmpty(access_token)) {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }else{
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
            finish();
        }
    };


}
