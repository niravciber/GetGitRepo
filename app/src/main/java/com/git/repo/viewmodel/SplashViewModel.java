package com.git.repo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.logging.Handler;

public class SplashViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getisLoading(){
        return  isLoading;
    }

    public void startHandler(){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setIsLoading(true);
            }
        },3000);
    }

    public void setIsLoading(boolean val){
        isLoading.setValue(val);
    }
}


