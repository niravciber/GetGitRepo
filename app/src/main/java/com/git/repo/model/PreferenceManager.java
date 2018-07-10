package com.git.repo.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.git.repo.GetGitRepoApp;

public class PreferenceManager {


    //Preference keys
    public static final String PREF_CODE = "pref_code";
    public static final String PREF_ACCESS_TOKEN = "access_token";


    public static Boolean getBooleanPrefs(Context ctx, String key) {
        return GetGitRepoApp.getSharedPreference(ctx).getBoolean(key, false);
    }

    public static String getStringPrefs(Context ctx, String key) {
        return GetGitRepoApp.getSharedPreference(ctx).getString(key, "");
    }

    public static String getStringPrefs(Context ctx, String key, String defaultValue) {
        return GetGitRepoApp.getSharedPreference(ctx).getString(key, defaultValue);
    }

    public static int getIntegerPrefs(Context ctx, String key) {
        return GetGitRepoApp.getSharedPreference(ctx).getInt(key, 0);
    }

    public static int getIntegerPrefs(Context ctx, String key, int defaultValue) {
        return GetGitRepoApp.getSharedPreference(ctx).getInt(key, defaultValue);
    }

    public static long getLongPrefs(Context ctx, String key) {
        return GetGitRepoApp.getSharedPreference(ctx).getLong(key, 0);
    }

    public static long getLongPrefs(Context ctx, String key, int defaultValue) {
        return GetGitRepoApp.getSharedPreference(ctx).getLong(key, defaultValue);
    }

    public static float getFLoatPrefs(Context ctx, String key) {
        return GetGitRepoApp.getSharedPreference(ctx).getFloat(key, 0);
    }


    public static void set(final Context context, final String key, final Object value) {
        SharedPreferences.Editor sharedPreferenceEditor = GetGitRepoApp.getSharedPreference(context)
                .edit();
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
        sharedPreferenceEditor.commit();
    }

    public static void setAsync(final Context context, final String key, final Object value) {
        SharedPreferences.Editor sharedPreferenceEditor = GetGitRepoApp.getSharedPreference(context)
                .edit();
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

    public static void clear(final Context context) {
        GetGitRepoApp.getSharedPreference(context).edit().clear().commit();

    }
}