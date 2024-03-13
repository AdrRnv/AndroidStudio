package com.example.tp5retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://worldtimeapi.org/api/timezone/";
    Button valider;
    EditText ville;
    TextView info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valider = findViewById(R.id.valider);
        ville = findViewById(R.id.ville);
        info = findViewById(R.id.info);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCityByName(ville.getText().toString());
            }
        });
    }

    private void getCityByName(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClockService clockService = retrofit.create(ApiClockService.class);
        Call<Clock> callParis = clockService.getCityByName(name);
        callParis.enqueue(new Callback<Clock>() {
            @Override
            public void onResponse(Call<Clock> call, Response<Clock> response) {
                info.setText(response.body().getDatetime());
            }

            @Override
            public void onFailure(Call<Clock> call, Throwable t) {

            }
        });
    }
}