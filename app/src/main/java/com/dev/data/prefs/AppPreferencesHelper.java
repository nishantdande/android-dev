package com.dev.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.di.ApplicationContext;
import com.dev.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_THEME = "PREF_KEY_THEME";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public void setTheme(String theme) {
        mPrefs.edit().putString(PREF_KEY_THEME, theme).apply();
    }

    @Override
    public String getTheme() {
        return mPrefs.getString(PREF_KEY_THEME, "AppTheme");
    }

    @Override
    public String getAccessToken() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply();
    }
}
