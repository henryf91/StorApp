package com.edu.unab.mgads.henryf.storapp.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class StorAppAPI {

    private static Retrofit instance;
    private static final String url = "https://storapp-4d9ac-default-rtdb.firebaseio.com";

    public static Retrofit getInstance(){



        if(instance == null){
            instance = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return instance;
    }

}
