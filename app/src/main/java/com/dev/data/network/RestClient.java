package com.dev.data.network;

import android.content.Context;

import com.dev.DevApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RestClient {

    private static ApiEndpoint mRestService = null;

    public static ApiEndpoint getClient() {
        if (mRestService == null) {
            final OkHttpClient client = new OkHttpClient
                    .Builder()
                    .addInterceptor(new NetworkConnectionInterceptor())
                    .addInterceptor(new FakeInterceptor(DevApplication.getContext()))
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://hacker-news.firebaseio.com/")
                    .client(client)
                    .build();

            mRestService = retrofit.create(ApiEndpoint.class);
        }
        return mRestService;
    }
}