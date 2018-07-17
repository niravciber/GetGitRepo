package com.git.repo.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.git.repo.model.PreferenceHelper;
import com.git.repo.model.Repo;
import com.git.repo.model.RepoResponse;
import com.git.repo.network.GithubApi;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {


    @Inject @Named("api_url")
    GithubApi githubApi;
    @Inject
    PreferenceHelper preferenceHelper;

    @Inject
    MainViewModel(){}
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Repo>> repoLivedata = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    public void fetchRepos() {
        loadingLiveData.setValue(true);

        String header = preferenceHelper.getStringPrefs(PreferenceHelper.PREF_ACCESS_TOKEN_TYPE)
                + " " + preferenceHelper.getStringPrefs(PreferenceHelper.PREF_ACCESS_TOKEN);
        compositeDisposable.add(githubApi
                .getDefaultRepos(header)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                    @Override
                    public void onSuccess(List<Repo> repos) {
                        loadingLiveData.setValue(false);
                        Log.i("onsuccess", "repos");
                        repoLivedata.setValue(repos);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingLiveData.setValue(false);
                        Log.i("onError", "repos are not there");
                    }
                }));
    }


    public void searchRepos(String query) {
        String header = preferenceHelper.getStringPrefs(PreferenceHelper.PREF_ACCESS_TOKEN_TYPE)
                + " " + preferenceHelper.getStringPrefs(PreferenceHelper.PREF_ACCESS_TOKEN);
        loadingLiveData.setValue(true);
        compositeDisposable.add(githubApi
                .searchRepos(header, query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<RepoResponse>() {
                    @Override
                    public void onSuccess(RepoResponse repos) {
                        loadingLiveData.setValue(false);
                        Log.i("onsuccess", "repos");
                        repoLivedata.setValue(repos.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingLiveData.setValue(false);
                        Log.i("onError", "repos are not there");
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public LiveData<List<Repo>> getRepoLivedata() {
        return repoLivedata;
    }

    public LiveData<Boolean> getLoading() {
        return loadingLiveData;
    }
}
