package com.dev.ui.main.dashboard;

import com.dev.data.network.model.Story;
import com.dev.di.PerActivity;
import com.dev.ui.base.MvpPresenter;

@PerActivity
public interface DashboardMvpPresenter<V extends DashboardMvpView> extends MvpPresenter<V> {

    void getTopStories(int pageCount);

    void storeStory(Story story);
}
