package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.smt;
import static com.example.labassistant.LoginActivity.usernm;
import static com.example.labassistant.HomepageActivity.semester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SemesterActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private Button btnNext;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semester);

        // Initialising all views through id defined above
        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        btnNext = findViewById(R.id.semester_btn);
        radioGroup = findViewById(R.id.RadioSemester);
//        RadioButton smt1 = (RadioButton)findViewById(R.id.srad1);
//        RadioButton smt2 = (RadioButton)findViewById(R.id.srad2);
//        RadioButton smt3 = (RadioButton)findViewById(R.id.srad3);
//        RadioButton smt4 = (RadioButton)findViewById(R.id.srad4);
//        RadioButton smt5 = (RadioButton)findViewById(R.id.srad5);

        radioGroup.clearCheck();
        // Mengirim data jobsheet ke database yang dipilih pada radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            // List semester berdasarkan data dari database
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = findViewById(id);
                switch (id){
                    case R.id.srad1:
                        semester = "1";
                        smt = radioButton.getText().toString();
                        Toast.makeText(getApplication(), semester, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.srad2:
                        semester = "2";
                        smt = radioButton.getText().toString();
                        Toast.makeText(getApplication(), semester, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.srad3:
                        semester = "3";
                        smt = radioButton.getText().toString();
                        Toast.makeText(getApplication(), semester, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.srad4:
                        semester = "4";
                        smt = radioButton.getText().toString();
                        Toast.makeText(getApplication(), semester, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.srad5:
                        semester = "5";
                        smt = radioButton.getText().toString();
                        Toast.makeText(getApplication(), semester, Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        // Tombol untuk beralih ke halaman matkul
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("semester");
                myRef.setValue(smt);

                Intent i = new Intent(SemesterActivity.this, MatkulActivity.class);
                startActivity(i);
            }
        });

    }
}