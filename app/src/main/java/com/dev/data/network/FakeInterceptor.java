package com.dev.data.network;

import android.accounts.AuthenticatorException;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

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
        // Get Request URI.

        Uri uri = Uri.parse(chain.request().url().uri().toString());
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString());

        if (Objects.requireNonNull(uri.getPath()).contains("login")){
            String responseFileName = null;
            if (uri.getQueryParameter("username").equals("test@worldofplay.in") &&
                    uri.getQueryParameter("password").equals("Worldofplay@2020")) {
                responseFileName = "success/login.json";
                String responseMessage = readJSON(responseFileName);
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
                String responseMessage = readJSON(responseFileName);
                throw new IOException(responseMessage);
            }
        }


        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString());
        return response;
    }

    String readJSON(String filename) throws IOException {
            // Opening data.json file
            InputStream inputStream = mContext.getAssets().open(filename);
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder responseStringBuilder = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                responseStringBuilder.append(line).append('\n');
            }
        return responseStringBuilder.toString();
    }
}
