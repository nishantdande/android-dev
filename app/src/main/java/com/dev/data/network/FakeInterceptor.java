package com.dev.data.network;

import android.accounts.AuthenticatorException;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.dev.utils.CommonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Objects;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FakeInterceptor implements Interceptor {
    private static final String TAG = FakeInterceptor.class.getSimpleName();
    private static final String FILE_EXTENSION = ".json";
    private Context mContext;

    private String mContentType = "application/json";

    public FakeInterceptor(Context context) {
        mContext = context;
    }

    /**
     * Set content type for header
     * @param contentType Content type
     * @return FakeInterceptor
     */
    public FakeInterceptor setContentType(String contentType) {
        mContentType = contentType;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String method = chain.request().method().toLowerCase();

        Response response = null;

        Uri uri = Uri.parse(chain.request().url().uri().toString());

        if (Objects.requireNonNull(uri.getPath()).contains("login")){
            String responseFileName = null;
            if (uri.getQueryParameter("username").equals("test@worldofplay.in") &&
                    uri.getQueryParameter("password").equals("Worldofplay@2020")) {
                responseFileName = "success/login.json";
                String responseMessage = CommonUtils.readJSON(responseFileName);
                response = new Response.Builder()
                        .code(200)
                        .message(responseMessage.toString())
                        .request(chain.request())
                        .protocol(Protocol.HTTP_2)
                        .body(ResponseBody.create(MediaType.parse(mContentType), responseMessage.toString().getBytes()))
                        .addHeader("content-type", mContentType)
                        .build();
            } else {
                responseFileName = "error/incorrect_credential.json";
                String responseMessage = CommonUtils.readJSON(responseFileName);
                throw new IOException(responseMessage);
            }
        } else {
            return chain.proceed(chain.request());
        }
        return response;
    }


}
