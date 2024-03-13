package com.iut.app.android.retrofittp2.manager;

import android.util.Log;

import com.iut.app.android.retrofittp2.model.Clock;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController {

    private final ApiManager apiManager;

    public MainActivityController() {
        apiManager = ApiManager.getInstance();
    }

    public void getTime(IClockDataManagerCallBack callBack) {
        Call<Clock> callTimeParis = apiManager.getClockService().getTimeCityEurope("london");
        callTimeParis.enqueue(new Callback<Clock>() {
            @Override
            public void onResponse(Call<Clock> call, Response<Clock> response) {
                if (response.isSuccessful()) {
                    Clock c = response.body();
                    Log.e("onResponse", c.getDatetime());
                    callBack.getTimeResponseSuccess(c);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Clock> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });

    }

    public void getTimeThread(IClockDataManagerCallBack callBack) {


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Response<Clock> response = apiManager.getClockService().getTimeParis().execute();
                    if (response.isSuccessful()) {
                        Clock c = response.body();
                        Log.e("onResponse", c.getDatetime());
                        callBack.getTimeResponseSuccess(c);
                    } else {
                        Log.e("onResponse", "Not successfull : " + response.code());
                        callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                    }

                } catch (IOException e) {
                    Log.e("onResponse", "Not successfull : " + e.getLocalizedMessage());
                    callBack.getTimeResponseError("Erreur lors de la requete : " + e.getLocalizedMessage());
                }

            }
        };

        new Thread(r).start();
    }
}

