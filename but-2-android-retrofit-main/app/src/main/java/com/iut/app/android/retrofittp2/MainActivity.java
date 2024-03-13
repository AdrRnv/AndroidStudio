package com.iut.app.android.retrofittp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;

import com.iut.app.android.retrofittp2.manager.MainActivityController;
import com.iut.app.android.retrofittp2.manager.IClockDataManagerCallBack;
import com.iut.app.android.retrofittp2.model.Clock;

public class MainActivity extends AppCompatActivity implements IClockDataManagerCallBack {

    private MainActivityController mainActivityController = new MainActivityController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTime();
    }



    private void getTime() {
        // implementation de l'interface au niveau de la class (et @override des methodes -> cf. ci-apres)
        mainActivityController.getTime(this);
    }

    private void getTime2() {
        //implementation anonyme -> @override des methodes lors de l'instanciation) 
        mainActivityController.getTime(new IClockDataManagerCallBack() {
            @Override
            public void getTimeResponseSuccess(Clock clock) {

            }

            @Override
            public void getTimeResponseError(String message) {

            }

        });
    }

    private void getTimeThread() {
        //Ecriture comme avec les listener

        mainActivityController.getTimeThread(new IClockDataManagerCallBack() {
            @Override
            public void getTimeResponseSuccess(Clock clock) {

            }

            @Override
            public void getTimeResponseError(String message) {

            }
        });

    }

    @Override
    public void getTimeResponseSuccess(Clock clock) {
        TextView t = findViewById(R.id.tv_hello);
        t.setText(clock.getDatetime());
    }

    @Override
    public void getTimeResponseError(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
        alert.setTitle("Erreur").setMessage("une erreur est apparue lors de la recherche de données");
        alert.setCancelable(false);
        alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.setIcon(R.drawable.ic_launcher_background);
        alert.create().show();
    }
}
