package com.iut.app.android.retrofittp2.manager;

import com.iut.app.android.retrofittp2.service.ClockService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    final static String BASE_URL = "http://worldtimeapi.org/api/timezone/";

    private ClockService clockService = null;

    private static ApiManager instance;

    public ClockService getClockService() {
        return clockService;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private ApiManager() {
        createRetrofitClock();
    }

    private void createRetrofitClock() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofitClock = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        clockService = retrofitClock.create(ClockService.class);
    }

}
