package com.example.projetandroidapi.manager;

import com.example.projetandroidapi.service.ResultService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    final static String BASE_URL = "https://public.opendatasoft.com/api/explore/v2.1/catalog/datasets/osm-france-cinema/records/";
    private ResultService resultService = null;
    private static ApiManager instance;

    public ResultService getResultService() {
        return resultService;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private ApiManager() {
        createRetrofitResult();
    }

    private void createRetrofitResult() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofitResult = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resultService = retrofitResult.create(ResultService.class);
    }

}
