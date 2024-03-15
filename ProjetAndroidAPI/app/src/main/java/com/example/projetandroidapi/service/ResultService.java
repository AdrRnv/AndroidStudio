package com.example.projetandroidapi.service;

import com.example.projetandroidapi.model.Cinema;
import com.example.projetandroidapi.model.Result;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ResultService {
    @GET("?group_by=marque&limit=20")
    Call<Cinema> getMarque();

    @GET("?group_by=meta_code_dep&limit=20")
    Call<Cinema> getDep();

    @GET("?limit=20")
    Call<Cinema> getAll();

    @GET("?group_by=name&limit=20")
    Call<Cinema> getName();

    @GET("?limit=20")
    Call<Cinema> getNextPage(@Query("offset") int offset);

}

/*
public interface ClockService {

    @GET("Europe/paris")
    Call<Cinema> getTimeParis();

    @GET("Europe/paris")
    Response<Cinema> getTimeParisThread();

    @GET("Europe/{city}")
    Call<Cinema> getTimeCityEurope(@Path("city") String citySelected);

} */