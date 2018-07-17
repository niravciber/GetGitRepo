package com.git.repo.view;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.git.repo.viewmodel.MainViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    RepoAdapter providesRepoAdapter(){
        return  new RepoAdapter();
    }

    @Provides
    LinearLayoutManager providesLinearLayoutManager(MainActivity mainActivity){
        return new LinearLayoutManager(mainActivity);
    }

    @Provides
    DividerItemDecoration providesDivideItemDecoration(MainActivity mainActivity){
        return new DividerItemDecoration(mainActivity,DividerItemDecoration.VERTICAL);
    }

}

