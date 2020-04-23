package com.dev.di.component;

import android.app.Application;
import android.content.Context;

import com.dev.DevApplication;
import com.dev.data.DataManager;
import com.dev.data.network.ApiEndpoint;
import com.dev.data.network.ApiHelper;
import com.dev.di.ApplicationContext;
import com.dev.di.module.ApplicationModule;
import com.dev.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    void inject(DevApplication app);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();

}