package com.git.repo.di.component;


import android.app.Activity;
import android.app.Application;

import com.git.repo.GetGitRepoApp;
import com.git.repo.di.builder.ActivityBuilder;
import com.git.repo.di.module.Appmodule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        Appmodule.class,
        ActivityBuilder.class})
public interface Appcomponent extends AndroidInjector<DaggerApplication> {

    void inject(GetGitRepoApp app);

    @Override
    void inject(DaggerApplication instance);

    @Component.Builder
    interface Builder {
        @BindsInstance
        abstract Builder application(Application application);
        Appcomponent build();
    }
}