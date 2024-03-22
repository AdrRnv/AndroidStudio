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
    ArrayList<Result> resultArrayList;
    Cinema cinema;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private LinearLayoutManager layoutManager;
    boolean load = false;
    private int itemCount = 0;
    private static final int LIMIT = 20;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.lv);
        layoutManager = new LinearLayoutManager(getContext()); // Assign it here
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ResultAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(adapter);

        getCinemaData("0");

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int lastVisibleItemPosition;

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Récupérer la position du dernier élément visible avant le chargement des nouveaux éléments
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                int itemCountBeforeLoad = adapter.getItemCount();

                Log.d("list", lastVisibleItemPosition + "");

                // Charger les nouveaux éléments si le dernier élément visible est proche de la fin de la liste
                if (lastVisibleItemPosition >= itemCountBeforeLoad-1 && !load) {
                    itemCount += LIMIT;
                    getCinemaData(itemCount + "");
                    load = true;
                }
            }
        });

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

    private void getCinemaData(String number) {
        MainActivityController mainActivityController = new MainActivityController();
        mainActivityController.getCinemaName(this, number);
    }

    public void getTimeResponseSuccess(Cinema cinema) {
        if (cinema != null && cinema.getResults() != null && !cinema.getResults().isEmpty()) {
            List<Result> resultList = cinema.getResults();
            resultArrayList = new ArrayList<>(resultList);
            adapter.addItems(resultArrayList);
            recyclerView.setAdapter(adapter);
            load = false;
            recyclerView.scrollToPosition(itemCount);
            Log.d("Résultat", "Résultat Cinema");
        } else {
            Log.d("Marque", "Aucun résultat ou liste de résultats vide dans Cinema");
        }

    }

    @Override
    public void getTimeResponseError(String message) {
        Log.d("Marque", "La marque est nulle");
    }


}