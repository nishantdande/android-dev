package com.dev.ui.main;

import com.dev.di.PerActivity;
import com.dev.ui.base.MvpPresenter;

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void getTheme();

    void setTheme(boolean value);
}
