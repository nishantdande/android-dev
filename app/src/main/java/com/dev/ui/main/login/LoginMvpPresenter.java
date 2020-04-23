package com.dev.ui.main.login;

import com.dev.di.PerActivity;
import com.dev.ui.base.MvpPresenter;

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClick(String username, String password);

    void getTheme();
}
