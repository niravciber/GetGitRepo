package com.git.repo.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.git.repo.GetGitRepoApp;

import javax.inject.Inject;

public class PreferenceHelper {


    //Preference keys
    public static final String PREF_CODE = "pref_code";
    public static final String PREF_ACCESS_TOKEN = "access_token";
    public static final String PREF_ACCESS_TOKEN_TYPE = "access_token_type";

    private SharedPreferences mSharedPreferences;

    @Inject
    PreferenceHelper(SharedPreferences mSharedPreference){
        this.mSharedPreferences = mSharedPreference;
    }

    public  Boolean getBooleanPrefs(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public  String getStringPrefs( String key) {
        return mSharedPreferences.getString(key, "");
    }

    public  String getStringPrefs( String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public  int getIntegerPrefs( String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public  int getIntegerPrefs(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public  long getLongPrefs( String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public  long getLongPrefs( String key, int defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public  float getFLoatPrefs( String key) {
        return mSharedPreferences.getFloat(key, 0);
    }


    public  void set( final String key, final Object value) {
        SharedPreferences.Editor sharedPreferenceEditor = mSharedPreferences.edit();
        if (value instanceof String) {
            sharedPreferenceEditor.putString(key, (String) value);
        } else if (value instanceof Long) {
            sharedPreferenceEditor.putLong(key, (Long) value);
        } else if (value instanceof Integer) {
            sharedPreferenceEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            sharedPreferenceEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            sharedPreferenceEditor.putFloat(key, (Float) value);
        }
        sharedPreferenceEditor.apply();
    }

    public  void setAsync( final String key, final Object value) {
        SharedPreferences.Editor sharedPreferenceEditor = mSharedPreferences.edit();
        if (value instanceof String) {
            sharedPreferenceEditor.putString(key, (String) value);
        } else if (value instanceof Long) {
            sharedPreferenceEditor.putLong(key, (Long) value);
        } else if (value instanceof Integer) {
            sharedPreferenceEditor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            sharedPreferenceEditor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            sharedPreferenceEditor.putFloat(key, (Float) value);
        }
        sharedPreferenceEditor.apply();
    }

    public  void clear() {
        mSharedPreferences.edit().clear().apply();

    }
}