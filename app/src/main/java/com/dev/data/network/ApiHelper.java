package com.dev.data.network;


import com.dev.data.network.model.LoginRequest;
import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface ApiHelper {

    Single<LoginResponse> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<ArrayList<Story>> getTopStories();

}
