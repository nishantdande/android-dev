package com.dev.data.network;

import com.dev.data.network.model.LoginResponse;
import com.dev.data.network.model.Story;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoint {

    @GET("v0/topstories.json")
    Call<ArrayList<String>> getTopStories();

    @GET("v0/item/{id}.json")
    Call<Story> getTopStories(@Path(value = "id") String id);

    @POST("login")
    Call<LoginResponse> login(@Query("username") String username,
                              @Query("password") String password );
}
