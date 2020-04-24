package com.dev.ui.main.dashboard;


import com.dev.data.DataManager;
import com.dev.data.network.model.Story;
import com.dev.ui.base.BasePresenter;
import com.dev.utils.AppLogger;
import com.dev.utils.rx.SchedulerProvider;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class DashboardPresenter<V extends DashboardMvpView> extends BasePresenter<V>
        implements DashboardMvpPresenter<V> {

    private static final String TAG = "DashboardPresenter";


    @Inject
    public DashboardPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getTopStories(int pageCount) {
        AtomicInteger i = new AtomicInteger();
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTopStories(pageCount)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(stories -> {
                    getMvpView().onSuccess(stories);
                }, throwable -> {
                    getMvpView().hideLoading();
                    Timber.e(throwable);
                }, () -> {
                    getMvpView().hideLoading();
                    AppLogger.d("completed");
                }));
    }

    @Override
    public void storeStory(Story story) {
        getDataManager().setStory(story);
    }
}
