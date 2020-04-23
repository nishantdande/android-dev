package com.dev.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dev.DevApplication;
import com.dev.utils.CommonUtils;

import java.io.IOException;
import java.net.UnknownHostException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

public class NetworkConnectionInterceptor implements Interceptor {

    private Context mContext;

    public NetworkConnectionInterceptor() {
        mContext = DevApplication.getContext();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isConnected()) {
            String responseMessage = CommonUtils.readJSON("error/bad_request.json");
            throw new IOException(responseMessage);
        }

        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }

    boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public static Exception handleNetworkError(Throwable throwable){
        if (throwable instanceof IOException){
            String responseMessage = null;
            try {
                responseMessage = CommonUtils.readJSON("error/bad_request.json");
                throw new IOException(responseMessage);
            } catch (IOException ex) {
                return new IOException(throwable.getMessage());
            }
        } else {
            return new Exception(throwable.getMessage());
        }
    }
}
