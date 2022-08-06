package com.example.labassistant.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labassistant.R;
import com.example.labassistant.model.History;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.type.DateTime;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class historyAdapter extends FirebaseRecyclerAdapter<History,historyAdapter.historyViewholder> {

    private String histanggal, hismatkul,hisjob,hissmt,hisstatus;
    public historyAdapter(
            @NonNull FirebaseRecyclerOptions<History> options)
    {
        super(options);
    }

    @Override
    protected void
    onBindViewHolder(@NonNull historyViewholder holder,
                     int position, @NonNull History model)
    {
        hisjob = model.getHisJobsheet();
        hismatkul = model.getHisMatkul();
        histanggal = model.getHisTanggal();
        hissmt = model.getHisSemester();
        hisstatus = model.getHisStatus();


        holder.tanggaltv.setText(histanggal);
        holder.matkultv.setText(hismatkul);
        holder.jobsheettv.setText(hisjob);
        holder.semestertv.setText(hissmt);
        holder.statustv.setText(hisstatus);


    }

    // Function to tell the class about the Card view (here
    // "History.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public historyViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new historyAdapter.historyViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "History.xml")
    class historyViewholder
            extends RecyclerView.ViewHolder {
        TextView tanggaltv, matkultv,semestertv,jobsheettv,statustv;
        public historyViewholder(@NonNull View itemView)
        {
            super(itemView);

            tanggaltv = itemView.findViewById(R.id.his_tanggal_tv);
            matkultv = itemView.findViewById(R.id.his_matkul_tv);
            semestertv = itemView.findViewById(R.id.his_semester_tv);
            jobsheettv = itemView.findViewById(R.id.his_jobsheet_tv);
            statustv = itemView.findViewById(R.id.his_status_tv);
        }
    }


}

