package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.matkul;
import static com.example.labassistant.HomepageActivity.matkulId;
import static com.example.labassistant.HomepageActivity.semester;
import static com.example.labassistant.LoginActivity.usernm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MatkulActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private Button btnNext2;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matkul);

        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        btnNext2 = findViewById(R.id.matkul_btn);
        radioGroup = findViewById(R.id.RadioMatkul);
        RadioButton mat1 = (RadioButton)findViewById(R.id.mrad1);
        RadioButton mat2 = (RadioButton)findViewById(R.id.mrad2);
        RadioButton mat3 = (RadioButton)findViewById(R.id.mrad3);
        RadioButton mat4 = (RadioButton)findViewById(R.id.mrad4);
        RadioButton mat5 = (RadioButton)findViewById(R.id.mrad5);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference("/master/semester/"+semester+"/matkul/1/matkul");
        DatabaseReference ref2 = database.getReference("/master/semester/"+semester+"/matkul/2/matkul");
        DatabaseReference ref3 = database.getReference("/master/semester/"+semester+"/matkul/3/matkul");
        DatabaseReference ref4 = database.getReference("/master/semester/"+semester+"/matkul/4/matkul");
        DatabaseReference ref5 = database.getReference("/master/semester/"+semester+"/matkul/5/matkul");

        radioGroup.clearCheck();
        // Mengirim data matkul ke database yang dipilih pada radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            // List mata kuliah berdasarkan data dari database
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = findViewById(id);
                switch (id){
                    case R.id.mrad1:
                        matkulId= "1";
                        matkul = radioButton.getText().toString();
                        Toast.makeText(getApplication(), matkul, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.mrad2:
                        matkulId= "2";
                        matkul = radioButton.getText().toString();
                        Toast.makeText(getApplication(), matkul, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.mrad3:
                        matkulId= "3";
                        matkul = radioButton.getText().toString();
                        Toast.makeText(getApplication(), matkul, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.mrad4:
                        matkulId= "4";
                        matkul = radioButton.getText().toString();
                        Toast.makeText(getApplication(), matkul, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.mrad5:
                        matkulId= "5";
                        matkul = radioButton.getText().toString();
                        Toast.makeText(getApplication(), matkul, Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        // Fungsi Klik Tombol Next
        btnNext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("matkul");
                myRef.setValue(matkul);

                Intent i = new Intent(MatkulActivity.this, JobsheetActivity.class);
                startActivity(i);
            }
        });

        // Read from the database
        // Membaca data dari database
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val1 = dataSnapshot.getValue(String.class);
                mat1.setText(val1);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal untuk membaca nilai
            }
        });

        // Read from the database
        // Membaca data dari database
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String val2 = dataSnapshot.getValue(String.class);
                if (val2 == null) {
                    mat2.setVisibility(View.GONE);
                } else {
                    mat2.setText(val2);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal untuk membaca nilai
            }
        });

        // Read from the database
        // Membaca dari database
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val3 = dataSnapshot.getValue(String.class);
                if (val3 == null) {
                    mat3.setVisibility(View.GONE);
                } else {
                    mat3.setText(val3);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });

        ref4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String val4 = dataSnapshot.getValue(String.class);
                if (val4 == null) {
                    mat4.setVisibility(View.GONE);
                } else {
                    mat4.setText(val4);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal untuk membaca nilai
            }
        });

        // Read from the database
        // Membaca dari database
        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val5 = dataSnapshot.getValue(String.class);
                if (val5 == null) {
                    mat5.setVisibility(View.GONE);
                } else {
                    mat5.setText(val5);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });
    }
}