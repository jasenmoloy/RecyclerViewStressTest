package com.jasenmoloy.recyclerviewstresstest.drivers.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.MainPresenter;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.MainPresenterImpl;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.MainView;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;
import com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview.MainRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    RecyclerView recyclerView;
    MainRecyclerViewAdapter recyclerAdapter;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.mainactivity_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.mainactivity_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MainRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerAdapter);

        presenter = new MainPresenterImpl(this);
        presenter.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void onDataItemLoaded(BaseModel data) {
        recyclerAdapter.addItem(data);
    }

    @Override
    public void onDataItemsLoaded(List<BaseModel> data) {
        recyclerAdapter.setItems(data);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
