package com.the.example.amorageolocater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.the.example.amorageolocater.R;
import com.the.example.amorageolocater.database.FillHelpCenterDB;
import com.the.example.amorageolocater.database.HelpCenterDataDb;

import java.util.List;

public class EditDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_edit_database);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.viewEditData_recyclerView_id);
        int numberOfColumn = 1;
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), numberOfColumn));
        int spanCount = 2; // 2 columns
        int spacing = 10; // 10px
        recyclerView.addItemDecoration(new SpaceItemDocoration(spanCount, spacing, true));
        FillHelpCenterDB fillHelpCenterDB =new FillHelpCenterDB(getApplicationContext());
        List<HelpCenterDataDb> listHelpCenter=fillHelpCenterDB.queryHelpCenters();
        EditDatabaseAdapter adapter= new EditDatabaseAdapter(listHelpCenter);
        recyclerView.setAdapter(adapter);

    }
}
