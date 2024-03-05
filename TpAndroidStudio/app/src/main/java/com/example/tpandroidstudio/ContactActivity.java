package com.example.tpandroidstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    public ArrayList<Contact> listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        listContact = new ArrayList<>();

        Contact ct = new Contact("ee","qzaze",",",",",",","505505","","");
        listContact.add(ct);

        ListView lv = findViewById(R.id.listView);

        adapter = new ContactAdapter(this, listContact);
        lv.setAdapter(adapter);

    }
}