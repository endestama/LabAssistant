package com.example.labassistant;

import static com.example.labassistant.HomepageActivity.jobsheet;
import static com.example.labassistant.HomepageActivity.matkul;
import static com.example.labassistant.HomepageActivity.smt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labassistant.adapter.toolsAdapter;
import com.example.labassistant.model.Tools;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BarangActivity extends AppCompatActivity {

    // Inisialisasi smeua item yang tertera pada halaman barang
    private Button btnNext4;
    private RecyclerView recyclerView;
    private toolsAdapter adapter;
    private DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);

        // Inisialisasi semua tampilan melalui id yang ditentukan di atas
        btnNext4 = findViewById(R.id.barang_btn);
        recyclerView = findViewById(R.id.toolList_rv);

        mbase = FirebaseDatabase.getInstance().getReference("pointer2").child(smt).child(matkul).child(jobsheet).child("tools");

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        // Ini adalah kelas yang disediakan oleh FirebaseUI untuk membuat
        // query dalam database untuk mengambil data yang sesuai
        FirebaseRecyclerOptions<Tools> options
                = new FirebaseRecyclerOptions.Builder<Tools>()
                .setQuery(mbase, Tools.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        // Menghubungkan objek kelas Adaptor yang diperlukan ke
        // kelas Adaptor itu sendiri
        adapter = new toolsAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        // Menghubungkan kelas Adaptor dengan tampilan Recycler*/
        recyclerView.setAdapter(adapter);

        // Tombol untuk mengkonfirmasi barang yang akan dipinjam
        btnNext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BarangActivity.this, QRActivity.class);
                startActivity(i);
            }
        });

    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    // Berfungsi untuk memberi tahu aplikasi untuk mulai mendapatkan
    // data dari database saat dimulainya aktivitas
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