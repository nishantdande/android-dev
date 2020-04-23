package com.dev.di.module;

import android.app.Application;
import android.content.Context;

import com.dev.data.AppDataManager;
import com.dev.data.DataManager;
import com.dev.data.network.ApiEndpoint;
import com.dev.data.network.ApiHelper;
import com.dev.data.network.AppApiHelper;
import com.dev.data.prefs.AppPreferencesHelper;
import com.dev.data.prefs.PreferencesHelper;
import com.dev.di.ApplicationContext;
import com.dev.di.PreferenceInfo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return "android-dev";
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}
