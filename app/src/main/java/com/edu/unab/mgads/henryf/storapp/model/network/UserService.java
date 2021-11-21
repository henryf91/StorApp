package com.edu.unab.mgads.henryf.storapp.model.network;

import com.edu.unab.mgads.henryf.storapp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @PUT("users/{uid}.json")
    Call<User> create(@Path("uid") String uid, @Body User myUser);

    @GET("users/{uid}.json")
    Call<User> getById(@Path("uid") String uid);

}
