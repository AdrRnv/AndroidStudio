package com.iut.app.android.retrofittp2.service;

import com.iut.app.android.retrofittp2.model.Clock;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClockService {

    @GET("Europe/paris")
    Call<Clock> getTimeParis();

    @GET("Europe/paris")
    Response<Clock> getTimeParisThread();

    @GET("Europe/{city}")
    Call<Clock> getTimeCityEurope(@Path("city") String citySelected);

}
