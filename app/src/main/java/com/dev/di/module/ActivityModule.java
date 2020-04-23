package com.dev.di.module;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dev.data.network.model.Story;
import com.dev.di.ActivityContext;
import com.dev.di.PerActivity;
import com.dev.ui.main.MainMvpPresenter;
import com.dev.ui.main.MainMvpView;
import com.dev.ui.main.MainPagerAdapter;
import com.dev.ui.main.MainPresenter;
import com.dev.ui.main.dashboard.DashboardAdapter;
import com.dev.ui.main.dashboard.DashboardMvpPresenter;
import com.dev.ui.main.dashboard.DashboardMvpView;
import com.dev.ui.main.dashboard.DashboardPresenter;
import com.dev.ui.main.details.DetailsMvpPresenter;
import com.dev.ui.main.details.DetailsMvpView;
import com.dev.ui.main.details.DetailsPresenter;
import com.dev.ui.main.login.LoginMvpPresenter;
import com.dev.ui.main.login.LoginMvpView;
import com.dev.ui.main.login.LoginPresenter;
import com.dev.utils.rx.AppSchedulerProvider;
import com.dev.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private FragmentActivity mActivity;

    public ActivityModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    FragmentActivity provideActivity() {
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
    MainPagerAdapter provideMainPagerAdapter(FragmentActivity activity) {
        return new MainPagerAdapter(activity);
    }

    @Provides
    @PerActivity
    DashboardMvpPresenter<DashboardMvpView> provideDashboardPresenter(
            DashboardPresenter<DashboardMvpView> presenter) {
        return presenter;
    }

    @Provides
    DashboardAdapter provideDashboardAdapter() {
        return new DashboardAdapter(new ArrayList<Story>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(FragmentActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    @PerActivity
    DetailsMvpPresenter<DetailsMvpView> provideDetailsPresenter(
            DetailsPresenter<DetailsMvpView> presenter) {
        return presenter;
    }
}
