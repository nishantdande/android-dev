package com.dev;

import android.app.Application;
import android.content.Context;

import com.dev.data.DataManager;
import com.dev.di.component.ApplicationComponent;
import com.dev.di.component.DaggerApplicationComponent;
import com.dev.di.module.ApplicationModule;
import com.dev.utils.AppLogger;

import javax.inject.Inject;

public class DevApplication extends Application {


    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

    public static Context getContext(){
        return context;
    }
}
