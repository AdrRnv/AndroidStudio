package com.example.firstprogram;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edName;
    private EditText editText;
    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");

        Button btn = findViewById(R.id.BtnContact) ;
        TextView prenom = findViewById(R.id.editPrenom);
        TextView nom = findViewById(R.id.editNom);
        TextView telephone = findViewById(R.id.editPhone);

        editText=findViewById(R.id.editDate);

        ImageView img = findViewById(R.id.imageView);
        img.setImageResource(R.drawable.avatar);



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
                /*RadioGroup rg = findViewById(R.id.radioGroup);
                RadioButton rb;
                int x = rg.getCheckedRadioButtonId();
                rb=findViewById(x);

                if(rb.getText().equals("Homme")){
                    img.setImageResource(R.drawable.homme);
                }else if(rb.getText().equals("Femme")){
                    img.setImageResource(R.drawable.femme);
                } */

                /*if(prenom.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || telephone.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Compléter les champs", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Super", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Prénom : " + prenom.getText().toString() + "\n" + "Nom : " + nom.getText().toString() + "\n" + "Telephone  : " + telephone.getText().toString())
                            .setTitle("Formulaire Complété");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } */

                Intent myItent = new Intent(MainActivity.this, ContactActivity.class);
                //myItent.putExtra("Msg", edit.getText().toString());
                //myItent.putExtra("Nom", "Main");
                mStartForResult.launch(myItent);
            }

        });
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        editText.setText(dateFormat.format(myCalendar.getTime()));
    }
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    });
}


