package com.dev.ui.main;


import com.dev.data.DataManager;
import com.dev.data.network.model.LoginRequest;
import com.dev.ui.base.BasePresenter;
import com.dev.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = "MainPresenter";


    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getTheme() {
        getMvpView().setTheme(getDataManager().getTheme());
    }

    @Override
    public void setTheme(boolean value) {
        getDataManager().setTheme(value ? "ThemeYellow" : "AppTheme");
    }
}
