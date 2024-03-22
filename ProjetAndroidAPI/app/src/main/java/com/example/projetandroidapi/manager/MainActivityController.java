package com.example.projetandroidapi.manager;

import android.util.Log;

import com.example.projetandroidapi.model.Cinema;
import com.example.projetandroidapi.model.Result;
import com.example.projetandroidapi.ui.dashboard.DashboardFragment;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityController {


    private final ApiManager apiManager;

    public MainActivityController() {
        apiManager = ApiManager.getInstance();
    }

    public void getCinemaMarque(IResultDataManagerCallBack callBack) {
        Call<Cinema> getCinemaMarque = apiManager.getResultService().getMarque();
        getCinemaMarque.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful()) {
                    Cinema s = response.body();
                    Log.e("onResponse", "zeazeazeaéeaze");
                    callBack.getTimeResponseSuccess(s);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });

    }

    public void getCinemaName(IResultDataManagerCallBack callBack, String number) {
        Call<Cinema> getCinemaName = apiManager.getResultService().getCinema(number);
        getCinemaName.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Cinema cinema = response.body();
                    if (cinema.getResults() != null) {
                        for (Result result : cinema.getResults()) {
                            String cinemaName = result.getName();
                        }
                    } else {
                        Log.e("onResponse", "No results found");
                    }
                    callBack.getTimeResponseSuccess(cinema);
                } else {
                    Log.e("onResponse", "Not successful: " + response.code());
                    callBack.getTimeResponseError("Erreur: Le serveur a répondu avec le statut : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("onFailure", "Erreur lors de la requête : " + t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requête : " + t.getLocalizedMessage());
            }
        });
    }

    public void getCinemaDeparetment(IResultDataManagerCallBack callBack) {
        Call<Cinema> getCinemaDeparetment = apiManager.getResultService().getDep();
        getCinemaDeparetment.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful()) {
                    Cinema s = response.body();
                    Log.e("onResponse", "zeazeazeaéeaze");
                    callBack.getTimeResponseSuccess(s);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });

    }

    public void getAll(IResultDataManagerCallBack callBack) {
        Call<Cinema> getAll = apiManager.getResultService().getAll();
        getAll.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful()) {
                    Cinema s = response.body();
                    Log.e("onResponse", "zeazeazeaéeaze");
                    callBack.getTimeResponseSuccess(s);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });
    }

    public void getName(IResultDataManagerCallBack callBack) {
        Call<Cinema> getName = apiManager.getResultService().getName();
        getName.enqueue(new Callback<Cinema>() {
            @Override
            public void onResponse(Call<Cinema> call, Response<Cinema> response) {
                if (response.isSuccessful()) {
                    Cinema s = response.body();
                    Log.e("onResponse", "zeazeazeaéeaze");
                    callBack.getTimeResponseSuccess(s);

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getTimeResponseError("Erreur le serveur a repondu status : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Cinema> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getTimeResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());
            }
        });
    }



}

