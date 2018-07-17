package com.git.repo;

import com.git.repo.di.component.Appcomponent;
import com.git.repo.di.component.DaggerAppcomponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class GetGitRepoApp extends DaggerApplication{

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        Appcomponent appcomponent= DaggerAppcomponent.builder().application(this).build();
        appcomponent.inject(this);
        return appcomponent;
    }


}
