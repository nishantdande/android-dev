package com.dev.ui.main.login;


import com.dev.R;
import com.dev.data.DataManager;
import com.dev.data.network.model.LoginRequest;
import com.dev.ui.base.BasePresenter;
import com.dev.utils.AppLogger;
import com.dev.utils.CommonUtils;
import com.dev.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    private static final String TAG = "LoginPresenter";


    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onServerLoginClick(String email, String password) {
        //validate email and password
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.invalid_email);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }
        getMvpView().showLoading();

        getCompositeDisposable().add(getDataManager()
                .doServerLoginApiCall(new LoginRequest.ServerLoginRequest(email, password))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe((loginResponse) -> {
                    getMvpView().hideLoading();
                    getMvpView().onSuccess(loginResponse);
                }, throwable -> {
                    getMvpView().hideLoading();
                    getMvpView().onError(throwable.getMessage());
                    AppLogger.e(throwable, "AppError", throwable);
                }));
    }

    @Override
    public void getTheme() {
        getMvpView().updateThemeUi(getDataManager().getTheme());
    }


}
