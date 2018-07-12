package com.the.example.amorageolocater;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.the.example.amorageolocater.database.FillHelpCenterDB;
import com.the.example.amorageolocater.database.HelpCenterDataDb;

import java.util.List;

public class ViewEditDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_database);
        Button editButton=(Button)findViewById(R.id.editData_Button_id);
        editButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(ViewEditDatabase.this,EditDatabase.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.editData_recyclerView_id);
        int numberOfColumn = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumn));
       // int spanCount = 4; // 2 columns
      //  int spacing = 10; // 10px
      //  recyclerView.addItemDecoration(new SpaceItemDocoration(spanCount, spacing, true));
        FillHelpCenterDB fillHelpCenterDB =new FillHelpCenterDB(getApplicationContext());
        List<HelpCenterDataDb> listHelpCenter=fillHelpCenterDB.queryHelpCenters();
        ViewEditDatabaseAdapter adapter= new ViewEditDatabaseAdapter(listHelpCenter);
        recyclerView.setAdapter(adapter);

    }
}
