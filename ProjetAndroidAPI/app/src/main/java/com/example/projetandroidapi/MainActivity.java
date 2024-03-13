package com.example.projetandroidapi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.projetandroidapi.model.Cinema;
import com.example.projetandroidapi.model.Result;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.projetandroidapi.manager.MainActivityController;
import com.example.projetandroidapi.manager.IResultDataManagerCallBack;
import com.example.projetandroidapi.model.Result;

import com.example.projetandroidapi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements IResultDataManagerCallBack{

    private MainActivityController mainActivityController = new MainActivityController();
    TextView t;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        getTime();
    }

    private void getTime() {
        mainActivityController.getCinemaMarque(this);
    }


    @Override
    public void getTimeResponseSuccess(Cinema cinema) {
        if (cinema != null && cinema.getResults() != null && !cinema.getResults().isEmpty()) {
            Result firstResult = cinema.getResults().get(1); // Récupérer le premier résultat de la liste
            if (firstResult.getMarque() != null) {
                String marque = firstResult.getMarque().toString(); // Convertir la marque en chaîne de caractères
                Log.d("Marque", marque);
                t = findViewById(R.id.tv_hello);
                t.setText(marque);
            } else {
                Log.d("Marque", "La marque est nulle");
            }
        } else {
            Log.d("Marque", "Aucun résultat ou liste de résultats vide dans Cinema");
        }
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