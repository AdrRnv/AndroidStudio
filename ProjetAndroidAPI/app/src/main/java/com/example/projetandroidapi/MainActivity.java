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

import java.util.List;

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

        getAll();
    }

    private void getAll() {
        mainActivityController.getAll(this);
    }

    @Override
    public void getTimeResponseSuccess(Cinema cinema) {
    }

    @Override
    public void getTimeResponseError(String message) {
    }
}