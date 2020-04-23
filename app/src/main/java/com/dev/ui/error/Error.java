package com.dev.ui.error;

import okhttp3.Response;

public class Error extends Exception {

    public static final String CONNECTION_ERROR = "connectionError";
    public static final String RESPONSE_FROM_SERVER_ERROR = "responseFromServerError";
    public static final String REQUEST_CANCELLED_ERROR = "requestCancelledError";
    public static final String PARSE_ERROR = "parseError";
    public static final String PREFETCH = "prefetch";
    public static final String FAST_ANDROID_NETWORKING = "FastAndroidNetworking";
    public static final String USER_AGENT = "User-Agent";
    public static final String SUCCESS = "success";
    public static final String OPTIONS = "OPTIONS";
    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    private String error;
    private String description;

    private String errorBody;

    private int errorCode = 0;

    private String errorDetail;

    private Response response;

    public Error() {
    }

    public Error(Response response) {
        this.response = response;
    }

    public Error(String message) {
        super(message);
    }

    public Error(String message, Response response) {
        super(message);
        this.response = response;
    }

    public Error(String message, Throwable throwable) {
        super(message, throwable);
    }

    public Error(String message, Response response, Throwable throwable) {
        super(message, throwable);
        this.response = response;
    }

    public Error(Response response, Throwable throwable) {
        super(throwable);
        this.response = response;
    }

    public Error(Throwable throwable) {
        super(throwable);
    }

    public Response getResponse() {
        return response;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getErrorDetail() {
        return this.errorDetail;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }


    public String getErrorBody() {
        return errorBody;
    }

    public void setErrorBody(String errorBody) {
        this.errorBody = errorBody;
    }


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
