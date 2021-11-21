package com.edu.unab.mgads.henryf.storapp.model.network;

import androidx.room.Delete;

import com.edu.unab.mgads.henryf.storapp.model.entity.Product;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @POST("products.json")
    Call<Object> create(@Body Product myProduct);

    @GET("products.json")
    Call<Map<String, Product>> getAll();

    @GET("products/{pid}.json")
    Call<Product> getById(@Path("pid") String id);

    @PUT("products/{pid}.json")
    Call<Product> update(@Path("pid") String id, @Body Product myProduct);

    @DELETE("products/{pid}.json")
    Call<Void> delete(@Path("pid") String id);
}
