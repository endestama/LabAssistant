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

public class toolsAdapter extends FirebaseRecyclerAdapter<Tools, toolsAdapter.ToolsViewholder>{

    private String tools;

    public toolsAdapter(
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
        holder.toolstv.setText(tools);

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
                .inflate(R.layout.item, parent, false);
        return new toolsAdapter.ToolsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "Coin.xml")
    class ToolsViewholder
            extends RecyclerView.ViewHolder {
        TextView toolstv;
        public ToolsViewholder(@NonNull View itemView)
        {
            super(itemView);

            toolstv = itemView.findViewById(R.id.tools_tv);
        }
    }

}
