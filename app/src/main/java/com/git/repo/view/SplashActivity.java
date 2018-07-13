package com.git.repo.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.git.repo.R;
import com.git.repo.databinding.ActivitySplashBinding;
import com.git.repo.model.PreferenceManager;
import com.git.repo.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {


    private ActivitySplashBinding binding ;
    private SplashViewModel splashViewmodel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        splashViewmodel = ViewModelProviders.of(this).get(SplashViewModel.class);
        splashViewmodel.getisLoading().observe(this,observer);
        splashViewmodel.startHandler();

    }


    final android.arch.lifecycle.Observer<Boolean> observer = new android.arch.lifecycle.Observer<Boolean>() {
        @Override
        public void onChanged(@Nullable Boolean aBoolean) {
            if(aBoolean){
                String access_token = PreferenceManager.getStringPrefs(SplashActivity.this,PreferenceManager.PREF_ACCESS_TOKEN);
                if(TextUtils.isEmpty(access_token)) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
                finish();
            }
        }
    };


}
