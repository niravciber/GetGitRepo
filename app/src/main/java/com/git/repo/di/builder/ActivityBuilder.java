package com.git.repo.di.builder;

import android.app.Activity;

import com.git.repo.view.LoginActivity;
import com.git.repo.view.MainActivity;
import com.git.repo.view.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    public abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    public abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    public abstract MainActivity bindMainActivity();




}
