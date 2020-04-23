package com.dev.ui.main.details;


import com.dev.data.DataManager;
import com.dev.ui.base.BasePresenter;
import com.dev.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class DetailsPresenter<V extends DetailsMvpView> extends BasePresenter<V>
        implements DetailsMvpPresenter<V> {

    private static final String TAG = "DetailsPresenter";


    @Inject
    public DetailsPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void getStory() {
        getMvpView().setStory(getDataManager().getStory());
    }

    @Override
    public void clearStory() {
        getDataManager().clearStory();
    }
}
