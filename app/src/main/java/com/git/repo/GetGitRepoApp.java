package com.git.repo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GetGitRepoApp extends Application {

    private static SharedPreferences mSharedPreference;
    private  static GetGitRepoApp mainInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mainInstance = this;
    }

    public static SharedPreferences getSharedPreference(Context context){
        if (mSharedPreference == null) {
            mSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mSharedPreference;
    }

    public static GetGitRepoApp getMainInstance(){
        return mainInstance;
    }
}
