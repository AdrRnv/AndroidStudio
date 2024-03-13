package com.example.projetandroidapi.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetandroidapi.R;
import com.example.projetandroidapi.ResultAdapter;
import com.example.projetandroidapi.databinding.FragmentDashboardBinding;
import com.example.projetandroidapi.manager.IResultDataManagerCallBack;
import com.example.projetandroidapi.manager.MainActivityController;
import com.example.projetandroidapi.model.Cinema;
import com.example.projetandroidapi.model.Result;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment implements IResultDataManagerCallBack {
    private MainActivityController mainActivityController = new MainActivityController();
    private FragmentDashboardBinding binding;
    Cinema cinema;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.lv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ResultAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        getAll();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getMarque() {
        mainActivityController.getCinemaMarque(this);
    }
    private void getDepartement() {
        mainActivityController.getCinemaDeparetment(this);
    }
    private void getAll() {
        mainActivityController.getAll(this);
    }
    private void getName() {
        mainActivityController.getName(this);
    }

    @Override
    public void getTimeResponseSuccess(Cinema cinema) {
        if (cinema != null && cinema.getResults() != null && !cinema.getResults().isEmpty()) {
            List<Result> results = cinema.getResults();
            for (Result result : results) {
                Log.d("Result", "Name: " + result.getName());
                Log.d("Result", "Marque: " + result.getMarque());
                // Ajoutez d'autres informations à imprimer si nécessaire
            }
            adapter = new ResultAdapter(results);
            recyclerView.setAdapter(adapter);
        } else {
            Log.d("Marque", "Aucun résultat ou liste de résultats vide dans Cinema");
        }
    }



    @Override
    public void getTimeResponseError(String message) {
    }
}