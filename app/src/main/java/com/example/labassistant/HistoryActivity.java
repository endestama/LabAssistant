package com.example.labassistant;

import static com.example.labassistant.LoginActivity.usernm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.labassistant.adapter.historyAdapter;
import com.example.labassistant.model.History;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryActivity extends AppCompatActivity {

    // Inisialisasi semua item yang tertera
    private Button btnBack;
    private RecyclerView recyclerView;
    private historyAdapter adapter; // Create Object of the Adapter class
    private DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Create a instance of the database and get
        // its reference
        // Buat instance database dan dapatkan
        // referensinya
        mbase = FirebaseDatabase.getInstance().getReference("user").child(usernm).child("history");

        recyclerView = findViewById(R.id.history_rc);
        btnBack = findViewById(R.id.his_back_btn);

        // To display the Recycler view linearly
        // Untuk menampilkan tampilan Recycler secara linier
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        // Ini adalah kelas yang disediakan oleh FirebaseUI untuk membuat
        // query dalam database untuk mengambil data yang sesuai
        FirebaseRecyclerOptions<History> options
                = new FirebaseRecyclerOptions.Builder<History>()
                .setQuery(mbase, History.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        // Menghubungkan objek kelas Adaptor yang diperlukan ke
        // kelas Adaptor itu sendiri
        adapter = new historyAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        // Menghubungkan kelas Adaptor dengan tampilan Recycler*/
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HistoryActivity.this,HomepageActivity.class);
                startActivity(i);
            }
        });
    }

    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    // Berfungsi untuk memberi tahu aplikasi agar berhenti mendapatkan
    // data dari database tentang penghentian aktivitas
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}