package com.example.tp4_foot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {

    private String[] equipes = {"Real","City","OL","PSG","Liverpool","Chelsea","Barcelona","New Castle"};
    private ArrayList<String> listEquipes = new ArrayList<>(Arrays.asList(equipes));
    //private ArrayAdapter<String> adapter;
    private EquipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = findViewById(R.id.listView);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listEquipes);
        adapter = new EquipeAdapter(this,listEquipes);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, listEquipes.get(position), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Equipe : " + listEquipes.get(position));
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        listEquipes.remove(position);
        adapter.notifyDataSetChanged();
        return true;
    }
}