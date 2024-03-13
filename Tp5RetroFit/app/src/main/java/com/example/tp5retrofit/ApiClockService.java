package com.example.tp5retrofit;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;

public interface ApiClockService {

    @GET("Europe/{city}")
    Call<Clock> getCityByName(@Path("city") String cityName);

    @GET("Europe/Paris")
    Call<Clock> getClockParis();
}
