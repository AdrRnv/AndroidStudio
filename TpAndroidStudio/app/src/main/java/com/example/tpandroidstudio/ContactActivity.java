package com.example.tpandroidstudio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    private ContactAdapter adapter;
    private ArrayList<Contact> listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btn = findViewById(R.id.addContact) ;

        Intent intent = getIntent();
        listContact = (ArrayList<Contact>) intent.getSerializableExtra("contacts");

        if (listContact == null) {
            listContact = new ArrayList<>();
        }

        ListView lv = findViewById(R.id.listView);

        adapter = new ContactAdapter(this, listContact);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                builder.setMessage("Prénom : " + listContact.get(0).getPrenom() +
                                "\n" + "Nom : " + listContact.get(0).getNom() +
                                "\n" + "Email  : " + listContact.get(0).getEmail() +
                                "\n" + "Adresse  : " + listContact.get(0).getAdresse() +
                                "\n" + "Code postal  : " + listContact.get(0).getCodePostal() +
                                "\n" + "Date de naisssance  : " + listContact.get(0).getDateNaissance()
                        )
                        .setTitle("Informations :");
                AlertDialog dialog = builder.create();
                dialog.show();;
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listContact.remove(position);
                adapter.notifyDataSetChanged();
                saveContacts(listContact);
                return true;
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(ContactActivity.this, MainActivity.class);
                startActivity(myItent);
            }
        });
    }

    private void saveContacts(ArrayList<Contact> contacts) {
        // Code pour sauvegarder la liste d'objets
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            // Ouvrir un flux de sortie vers un fichier dans le système de fichiers de l'application
            fos = openFileOutput("saveFile", Context.MODE_PRIVATE);
            out = new ObjectOutputStream(fos);
            // Écrire la liste d'objets dans le fichier
            out.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Fermer les flux
            try {
                if (out != null) out.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
