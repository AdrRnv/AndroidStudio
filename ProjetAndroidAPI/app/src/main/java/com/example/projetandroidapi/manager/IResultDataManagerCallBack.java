package com.example.projetandroidapi.manager;

import com.example.projetandroidapi.model.Cinema;
import com.example.projetandroidapi.model.Result;

public interface IResultDataManagerCallBack {

    void getTimeResponseSuccess(Cinema cinema);
    void getTimeResponseError(String message);

}
