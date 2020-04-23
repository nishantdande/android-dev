package com.dev.ui.main.details;

import com.dev.di.PerActivity;
import com.dev.ui.base.MvpPresenter;

@PerActivity
public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {

    void getStory();

    void clearStory();
}
