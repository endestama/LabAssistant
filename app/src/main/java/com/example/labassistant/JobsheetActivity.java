package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.jobsheet;
import static com.example.labassistant.HomepageActivity.matkulId;
import static com.example.labassistant.HomepageActivity.semester;
import static com.example.labassistant.LoginActivity.usernm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JobsheetActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private Button btnNext3;
    private RadioGroup radioGroup;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobsheet);

        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        btnNext3 = findViewById(R.id.Jobsheet_btn);
        radioGroup = findViewById(R.id.RadioJobsheet);
        RadioButton job1 = (RadioButton)findViewById(R.id.jrad1);
        RadioButton job2 = (RadioButton)findViewById(R.id.jrad2);
        RadioButton job3 = (RadioButton)findViewById(R.id.jrad3);
        RadioButton job4 = (RadioButton)findViewById(R.id.jrad4);
        RadioButton job5 = (RadioButton)findViewById(R.id.jrad5);
        RadioButton job6 = (RadioButton)findViewById(R.id.jrad6);
        RadioButton job7 = (RadioButton)findViewById(R.id.jrad7);
        RadioButton job8 = (RadioButton)findViewById(R.id.jrad8);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/1/jobsheet");
        DatabaseReference ref2 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/2/jobsheet");
        DatabaseReference ref3 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/3/jobsheet");
        DatabaseReference ref4 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/4/jobsheet");
        DatabaseReference ref5 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/5/jobsheet");
        DatabaseReference ref6 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/6/jobsheet");
        DatabaseReference ref7 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/7/jobsheet");
        DatabaseReference ref8 = database.getReference("/master/semester/"+semester+"/matkul/"+matkulId+"/jobsheet/8/jobsheet");

        radioGroup.clearCheck();
        // Mengirim data jobsheet ke database yang dipilih pada radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            // List jobsheet berdasarkan data dari database
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                RadioButton radioButton = findViewById(id);
                switch (id){
                    case R.id.jrad1:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                    case R.id.jrad2:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                    case R.id.jrad3:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();
                        
                        break;
                    case R.id.jrad4:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.jrad5:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.jrad6:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.jrad7:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.jrad8:
                        jobsheet = radioButton.getText().toString();
                        Toast.makeText(getApplication(), jobsheet, Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        // Mengkonfirmasi jobsheet yang dipilih lalu berpindah halaman
        btnNext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(usernm).child("jobsheet");
                myRef.setValue(jobsheet);

                Intent i = new Intent(JobsheetActivity.this, BarangActivity.class);
                startActivity(i);
            }
        });

        // Read from the database
        // Membaca data dari database
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val1 = dataSnapshot.getValue(String.class);
                job1.setText(val1);
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
                job2.setText(val2);
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
                    job3.setVisibility(View.GONE);
                } else {
                    job3.setText(val3);
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
                String val4 = dataSnapshot.getValue(String.class);
                if (val4 == null) {
                    job4.setVisibility(View.GONE);
                } else {
                    job4.setText(val4);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });

        ref5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val5 = dataSnapshot.getValue(String.class);
                if (val5 == null) {
                    job5.setVisibility(View.GONE);
                } else {
                    job5.setText(val5);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });

        ref6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val6 = dataSnapshot.getValue(String.class);
                if (val6 == null) {
                    job6.setVisibility(View.GONE);
                } else {
                    job6.setText(val6);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });

        ref7.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val7 = dataSnapshot.getValue(String.class);
                if (val7 == null) {
                    job7.setVisibility(View.GONE);
                } else {
                    job7.setText(val7);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Gagal membaca nilai
            }
        });

        ref8.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val8 = dataSnapshot.getValue(String.class);
                if (val8 == null) {
                    job8.setVisibility(View.GONE);
                } else {
                    job8.setText(val8);
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