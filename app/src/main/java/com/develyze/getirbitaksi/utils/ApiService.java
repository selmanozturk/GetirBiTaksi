package com.develyze.getirbitaksi.utils;

import com.develyze.getirbitaksi.models.ResultObject;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created HyperX | 04.02.2018.
 */

public interface ApiService {

    @POST("https://getir-bitaksi-hackathon.herokuapp.com/searchRecords")
    Call<ResultObject> getResults(@Query("startDate") String startDate,
                                       @Query("endDate") String endDate,
                                       @Query("minCount") long min,
                                       @Query("maxCount") long max
                                       );

}