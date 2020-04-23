package com.dev.data.network;


import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;

import io.reactivex.Single;

public interface ApiHelper {

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

}
