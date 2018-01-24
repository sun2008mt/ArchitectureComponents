package com.whu.architecturecomponents.model.http;

import com.whu.architecturecomponents.model.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/1/24.
 */

public interface WebService {
    @GET("/user/{user}")
    Call<User> getUser(@Path("user") String userId);
}

