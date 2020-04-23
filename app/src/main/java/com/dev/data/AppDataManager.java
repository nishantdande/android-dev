package com.dev.data;

import android.content.Context;

import com.dev.data.network.ApiHelper;
import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;
import com.dev.data.prefs.PreferencesHelper;
import com.dev.di.ApplicationContext;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final Context mContext;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }


    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return mApiHelper.doServerLoginApiCall(request);
    }

    @Override
    public Observable<ArrayList<Story>> getTopStories() {
        return mApiHelper.getTopStories();
    }

    @Override
    public void setTheme(String theme) {
        mPreferencesHelper.setTheme(theme);
    }

    @Override
    public String getTheme() {
        return mPreferencesHelper.getTheme();
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public void setStory(Story story) {
        mPreferencesHelper.setStory(story);
    }

    @Override
    public Story getStory() {
        return mPreferencesHelper.getStory();
    }

    @Override
    public void clearStory() {
        mPreferencesHelper.clearStory();
    }
}
