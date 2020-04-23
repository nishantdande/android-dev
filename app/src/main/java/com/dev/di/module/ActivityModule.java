package com.dev.di.module;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.di.ActivityContext;
import com.dev.di.PerActivity;
import com.dev.ui.main.MainMvpPresenter;
import com.dev.ui.main.MainMvpView;
import com.dev.ui.main.MainPagerAdapter;
import com.dev.ui.main.MainPresenter;
import com.dev.ui.main.login.LoginMvpPresenter;
import com.dev.ui.main.login.LoginMvpView;
import com.dev.ui.main.login.LoginPresenter;
import com.dev.utils.rx.AppSchedulerProvider;
import com.dev.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(
            LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    MainPagerAdapter provideMainPagerAdapter(AppCompatActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }

}
