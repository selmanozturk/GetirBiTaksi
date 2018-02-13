package com.develyze.getirbitaksi;

import android.app.Application;

import com.develyze.getirbitaksi.utils.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by selman on 04.02.2018.
 */

public class GetirBiTaksiApplication extends Application {
    private static GetirBiTaksiApplication application;

    private ApiService apiService;


    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://getir-bitaksi-hackathon.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static GetirBiTaksiApplication Instance(){
        return application;
    }

    public ApiService getApiService() {
        return apiService;
    }

}