package com.dev.data.network;


import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;
import com.dev.di.ApplicationContext;
import com.dev.ui.error.APIError;
import com.dev.ui.error.Error;
import com.dev.utils.AppLogger;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Timer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @SuppressLint("CheckResult")
    @Override
    public Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {

        return Single.create(e -> {

            final Call<LoginResponse> call = RestClient.getClient().login(request.getUsername(), request.getPassword());
            call.enqueue(new retrofit2.Callback<LoginResponse>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponse> call,
                                       @NonNull Response<LoginResponse> response) {
                    if (response.isSuccessful()){
                        e.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<LoginResponse> call,
                                      @NonNull Throwable t) {
                    APIError error = new Gson().fromJson(t.getMessage(), APIError.class);
                    e.onError(new Exception(error.getDescription()));
                }
            });

        });
    }
}

