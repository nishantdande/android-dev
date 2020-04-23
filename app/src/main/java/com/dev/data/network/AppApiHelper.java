package com.dev.data.network;


import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;
import com.dev.di.ApplicationContext;
import com.dev.ui.error.APIError;
import com.dev.utils.AppLogger;
import com.dev.utils.CommonUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper(@ApplicationContext Context context) {

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

    @Override
    public Observable<ArrayList<Story>> getTopStories() {
        AtomicInteger count = new AtomicInteger();
        return Observable.create(new ObservableOnSubscribe<ArrayList<Story>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<Story>> e) throws Exception {
                final Call<ArrayList<String>> call = RestClient.getClient().getTopStories();
                call.enqueue(new retrofit2.Callback<ArrayList<String>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<String>> call,
                                           @NonNull Response<ArrayList<String>> response) {
                        if (response.body() != null){
                            int i = 0;
                            ArrayList<Story> stories = new ArrayList<>();
                            for (String id: response.body() ) {

                                if (i <= 20) {
                                    final Call<Story> story = RestClient.getClient().getTopStories(id);
                                    story.enqueue(new Callback<Story>() {
                                        @Override
                                        public void onResponse(Call<Story> call, Response<Story> response) {
                                            stories.add(response.body());
                                            if (count.get() == 20) {
                                                e.onNext(stories);
                                                e.onComplete();
                                            }

                                            count.getAndIncrement();
                                        }

                                        @Override
                                        public void onFailure(Call<Story> call, Throwable t) {
                                            e.onError(new Exception(t.getMessage()));
                                        }
                                    });
                                    i++;
                                } else {
                                    break;
                                }
                            }
                        } else {
                            e.onError(new Exception("Net Error"));
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<String>> call,
                                          @NonNull Throwable t) {
                        e.onError(NetworkConnectionInterceptor.handleNetworkError(t));
                    }
                });

            }
        });
    }
}

