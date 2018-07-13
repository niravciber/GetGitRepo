package com.git.repo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.git.repo.GetGitRepoApp;
import com.git.repo.model.PreferenceManager;
import com.git.repo.model.Repo;
import com.git.repo.model.RepoResponse;
import com.git.repo.network.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {


    CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<List<Repo>> repoLivedata = new MutableLiveData<>();


    public void fetchRepos(){
            String header = PreferenceManager.getStringPrefs(GetGitRepoApp.getMainInstance(),PreferenceManager.PREF_ACCESS_TOKEN_TYPE)
              +" "+PreferenceManager.getStringPrefs(GetGitRepoApp.getMainInstance(),PreferenceManager.PREF_ACCESS_TOKEN);

            compositeDisposable.add(RetrofitClient.getApiRetrofit()
                    .getDefaultRepos(header)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<List<Repo>>() {
                        @Override
                        public void onSuccess(List<Repo> repos) {
                            Log.i("onsuccess","repos");
                            repoLivedata.setValue(repos);
                        }

                        @Override
                        public void onError(Throwable e) {
                             Log.i("onError","repos are not there");
                        }
                    }));
    }



    public void searchRepos(String query){
            String header = PreferenceManager.getStringPrefs(GetGitRepoApp.getMainInstance(),PreferenceManager.PREF_ACCESS_TOKEN_TYPE)
              +" "+PreferenceManager.getStringPrefs(GetGitRepoApp.getMainInstance(),PreferenceManager.PREF_ACCESS_TOKEN);

            compositeDisposable.add(RetrofitClient.getApiRetrofit()
                    .searchRepos(header,query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<RepoResponse>() {
                        @Override
                        public void onSuccess(RepoResponse repos) {
                            Log.i("onsuccess","repos");
                            repoLivedata.setValue(repos.getItems());
                        }

                        @Override
                        public void onError(Throwable e) {
                             Log.i("onError","repos are not there");
                        }
                    }));
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable!=null){
            compositeDisposable.clear();
            compositeDisposable = null;
        }
    }

    public LiveData<List<Repo>> getRepoLivedata (){
        return repoLivedata;
    }
}
