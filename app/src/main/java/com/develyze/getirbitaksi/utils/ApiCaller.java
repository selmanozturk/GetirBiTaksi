package com.develyze.getirbitaksi.utils;

import com.develyze.getirbitaksi.GetirBiTaksiApplication;
import com.develyze.getirbitaksi.models.ResultObject;

import retrofit2.Callback;

/**
 * Created HyperX | 04.02.2018.
 */

public class ApiCaller {
    private ApiService apiService;

    public ApiCaller(GetirBiTaksiApplication app) {
        this.apiService = app.getApiService();
    }

    public void getResult(String startDate,
                              String endDate,
                              long minCount,
                              long maxCount,
                              Callback<ResultObject> callback) {
        apiService.getResults(startDate, endDate,minCount,maxCount).enqueue(callback);
    }
}
