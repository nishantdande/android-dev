package com.dev.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.data.network.model.Story;
import com.dev.di.ApplicationContext;
import com.dev.di.PreferenceInfo;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private static final String PREF_KEY_THEME = "PREF_KEY_THEME";
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";
    private static final String PREF_KEY_STORY = "PREF_KEY_STORY";

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

    @Override
    public void setStory(Story story) {
        mPrefs.edit().putString(PREF_KEY_STORY, new Gson().toJson(story)).apply();
    }

    @Override
    public Story getStory() {
        String story = mPrefs.getString(PREF_KEY_STORY, null);
        if (story != null){
           return new Gson().fromJson(story, Story.class);
        }
        return null;
    }

    @Override
    public void clearStory() {
        mPrefs.edit().putString(PREF_KEY_STORY, null).apply();
    }
}
