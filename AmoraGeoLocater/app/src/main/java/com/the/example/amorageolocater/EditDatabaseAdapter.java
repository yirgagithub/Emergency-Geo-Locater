package com.the.example.amorageolocater;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.the.example.amorageolocater.database.HelpCenterDataDb;

import java.util.List;

/**
 * Created by what on 11/8/2017.
 */

public class EditDatabaseAdapter  extends RecyclerView.Adapter<EditDatabaseAdapter.ListViewHolder>{

    private List<HelpCenterDataDb> list;
    EditDatabaseAdapter(List<HelpCenterDataDb> list)
    {
        this.list=list;
    }

     class ListViewHolder extends RecyclerView.ViewHolder
    {
        private EditText first;
        private EditText second;
        private EditText third;
        private EditText fourth;

        ListViewHolder(View itemView) {
            super(itemView);

            first=(EditText)itemView.findViewById(R.id.first_edit);
            second=(EditText)itemView.findViewById(R.id.second_edit);
            third=(EditText)itemView.findViewById(R.id.third_edit);
            fourth=(EditText)itemView.findViewById(R.id.fourth_edit);


            first.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {

                }
            });

        }


    }
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.view_database_edit,parent,false);
        return new EditDatabaseAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position)
    {
        HelpCenterDataDb helpCenterDataDb=list.get(position);
        holder.first.setText(helpCenterDataDb.getPhoneNumber());
        holder.second.setText(helpCenterDataDb.getUrlPath());
        holder.third.setText(String.valueOf(helpCenterDataDb.getLatitude()));
        holder.fourth.setText(String.valueOf(helpCenterDataDb.getLongitude()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }





}
