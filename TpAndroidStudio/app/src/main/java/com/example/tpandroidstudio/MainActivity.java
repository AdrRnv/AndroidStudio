package com.example.tpandroidstudio;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tpandroidstudio.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private EditText editText;
    final Calendar myCalendar= Calendar.getInstance();

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");

        Button btn = findViewById(R.id.BtnValidation) ;
        Button btnContact = findViewById(R.id.BtnContact);
        TextView prenom = findViewById(R.id.editPrenom);
        TextView nom = findViewById(R.id.editNom);
        TextView telephone = findViewById(R.id.editPhone);
        TextView email = findViewById(R.id.editEmail);
        TextView adresse = findViewById(R.id.editAdresse);
        TextView codepostal = findViewById(R.id.editCodePostal);

        editText=findViewById(R.id.editDate);

        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.ic_launcher_background);



        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.radioGroup);
                RadioButton rb;
                int x = rg.getCheckedRadioButtonId();
                rb=findViewById(x);

                /*
                if(rb.getText().equals("Homme")){
                    img.setImageResource(R.drawable.ic_launcher_background);
                }else if(rb.getText().equals("Femme")){
                    img.setImageResource(R.drawable.ic_launcher_background);
                }

                if(prenom.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || telephone.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Compléter les champs", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Super", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Prénom : " + prenom.getText().toString() + "\n" + "Nom : " + nom.getText().toString() + "\n" + "Telephone  : " + telephone.getText().toString())
                            .setTitle("Formulaire Complété");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } */

                contact = new Contact(nom.getText().toString(),prenom.getText().toString(),rb.getText().toString(),email.getText().toString(),adresse.getText().toString(),telephone.getText().toString(),codepostal.getText().toString(),editText.getText().toString());
            }
        });

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myItent = new Intent(MainActivity.this, ContactActivity.class);
                mStartForResult.launch(myItent);
            }
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

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
}


