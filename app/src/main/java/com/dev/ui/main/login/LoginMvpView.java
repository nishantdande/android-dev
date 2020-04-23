package com.dev.ui.main.login;


import com.dev.data.network.model.LoginResponse;
import com.dev.ui.base.MvpView;

public interface LoginMvpView extends MvpView {

    void onSuccess(LoginResponse loginResponse);

    void updateThemeUi(String theme);
}
