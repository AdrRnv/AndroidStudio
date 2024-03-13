package com.example.tpandroidstudio;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.tpandroidstudio.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private EditText editText;
    final Calendar myCalendar = Calendar.getInstance();
    private ArrayList<Contact> listContact = new ArrayList<>();
    private Contact contact;
    Uri imageUri = null;
    String image = "";
    ImageView img;
    boolean galery = false;
    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int PERMISSION_CODE = 1234;
    private static final int CAPTURE_CODE = 1001;
    private static final int PERMISSION_CODE_FOLDER = 1111;
    private static final String FILE_NAME = "saveFile";

    private static final String PHONE_REGEX = "^(0|(\\+|00)33)[1-9]([-. ]?[0-9]{2}){4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");

        Button btn = findViewById(R.id.BtnValidation);
        Button btnContact = findViewById(R.id.BtnContact);
        TextView prenom = findViewById(R.id.editPrenom);
        TextView nom = findViewById(R.id.editNom);
        TextView telephone = findViewById(R.id.editPhone);
        TextView email = findViewById(R.id.editEmail);
        TextView adresse = findViewById(R.id.editAdresse);
        TextView codepostal = findViewById(R.id.editCodePostal);

        editText = findViewById(R.id.editDate);


        img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.avatar);

        Button bTake = findViewById(R.id.photo);
        Button bOpen = findViewById(R.id.galerie);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un Intent pour ouvrir l'application de la galerie ou l'appareil photo
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT); // Ouvrir la galerie

                // Ou utilisez cette action pour ouvrir l'application de l'appareil photo
                // intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

                // Lancer l'activité avec l'intent
                mStartForResult.launch(Intent.createChooser(intent, "Choisir une image"));
            }
        });

        listContact = loadContacts();
        if (listContact == null) {
            listContact = new ArrayList<>();
        }

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.radioGroup);
                RadioButton rb;
                int x = rg.getCheckedRadioButtonId();
                rb = findViewById(x);

                if(imageUri==null){
                    if(rb.getText().equals("Homme")){
                        img.setImageResource(R.drawable.homme);
                        image="homme";
                    }else if(rb.getText().equals("Femme")){
                        img.setImageResource(R.drawable.femme);
                        image="femme";
                    }
                }else{
                    image=imageUri.toString();
                }

                if (prenom.getText().toString().isEmpty()) {
                    prenom.setError("Veuillez saisir le prénom");
                } else if (nom.getText().toString().isEmpty()) {
                    nom.setError("Veuillez saisir le nom");
                } else if (telephone.getText().toString().isEmpty()) {
                    telephone.setError("Veuillez saisir le numéro de téléphone");
                } else if (!isValidPhoneNumber(telephone.getText().toString())) {
                    telephone.setError("Numéro de téléphone invalide");
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Veuillez saisir l'email");
                } else if (adresse.getText().toString().isEmpty()) {
                    adresse.setError("Veuillez saisir l'adresse");
                } else if (codepostal.getText().toString().isEmpty()) {
                    codepostal.setError("Veuillez saisir le code postal");
                } else if (editText.getText().toString().isEmpty()) {
                    editText.setError("Veuillez saisir la date de naissance");
                } else {
                    // Tous les champs sont remplis, et le numéro de téléphone est valide
                    Toast.makeText(MainActivity.this, "Ajout du contact réussi", Toast.LENGTH_SHORT).show();
                    contact = new Contact(image.toString(), nom.getText().toString(), prenom.getText().toString(), rb.getText().toString(), email.getText().toString(), adresse.getText().toString(), telephone.getText().toString(), codepostal.getText().toString(), editText.getText().toString());
                    listContact.add(contact);
                    saveContacts(listContact);
                }


            }
        });
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(MainActivity.this, ContactActivity.class);
                myItent.putExtra("contacts", listContact);
                startActivity(myItent);
            }
        });

        bTake.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    openCamera();
                    galery=false;
                }
            }
        });

        bOpen.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
            galery = true;
        });
    }


    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == MainActivity.RESULT_OK) {
                Intent intent = result.getData();
            }
        }
    });

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && galery) {
            Toast.makeText(this, "Photo Valide", Toast.LENGTH_SHORT).show();
            imageUri = data.getData();
            this.grantUriPermission(this.getPackageName(), imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
            this.getContentResolver().takePersistableUriPermission(imageUri, takeFlags);
            img.setImageURI(imageUri);
        }

        if (resultCode == RESULT_OK && !galery){
            Toast.makeText(this, "Photo Valide", Toast.LENGTH_SHORT).show();
            img.setImageURI(imageUri);
        }

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

    // Ajoutez cette méthode pour charger la liste de contacts depuis le fichier de sauvegarde
    private ArrayList<Contact> loadContacts() {
        ArrayList<Contact> loadedContacts = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = openFileInput("saveFile");
            in = new ObjectInputStream(fis);
            loadedContacts = (ArrayList<Contact>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return loadedContacts;
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"new image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent camintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camintent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(camintent, CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode ==PERMISSION_CODE) {
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}


