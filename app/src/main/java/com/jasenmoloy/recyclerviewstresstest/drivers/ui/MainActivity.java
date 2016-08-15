package com.jasenmoloy.recyclerviewstresstest.drivers.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.domain.TestData;
import com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview.MainRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.mainactivity_recyclerview);
        recyclerView.setHasFixedSize(true); //Improves performance if you know the layout size will not change.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        loadData();

        System.out.println("Setting recycler view adapter");

        recyclerView.setAdapter(new MainRecyclerViewAdapter(TestData.buildTestData()));

    }

    public void loadData() {
        //TODO: Load some data
    }
}
