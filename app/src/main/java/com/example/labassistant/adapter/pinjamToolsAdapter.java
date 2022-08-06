package com.example.labassistant.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labassistant.R;
import com.example.labassistant.model.Tools;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class pinjamToolsAdapter extends FirebaseRecyclerAdapter<Tools, pinjamToolsAdapter.ToolsViewholder>{

    private String tools;

    public pinjamToolsAdapter(
            @NonNull FirebaseRecyclerOptions<Tools> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "Coin.xml") iwth data in
    // model class(here "Coin.class")
    @Override
    protected void
    onBindViewHolder(@NonNull ToolsViewholder holder,
                     int position, @NonNull Tools model)
    {
        tools = model.getToolsName();

        // Add firstname from model class (here
        // "Coin.class")to appropriate view in Card
        // view (here "Coin.xml")
        holder.ntoolstv.setText(tools);

    }

    // Function to tell the class about the Card view (here
    // "Coin.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public ToolsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item, parent, false);
        return new pinjamToolsAdapter.ToolsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "Coin.xml")
    class ToolsViewholder
            extends RecyclerView.ViewHolder {
        TextView ntoolstv;
        public ToolsViewholder(@NonNull View itemView)
        {
            super(itemView);

            ntoolstv = itemView.findViewById(R.id.Tools_tv);
        }
    }

}
