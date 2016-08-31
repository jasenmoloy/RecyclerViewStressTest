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

    private static final String SAVED_RECYCLER_LAYOUT = "view_recycler_layout";

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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(!recyclerView.canScrollVertically(1)) { //Check if we can scroll down
                    onScrolledToBottom();
                }
            }

            void onScrolledToBottom() {
                presenter.onRecyclerViewScrolledToBottom();
            }
        });

        presenter = new MainPresenterImpl(this);
        presenter.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            //Set the saved layout state. Note: This must done after the data has been populated!
            recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable(SAVED_RECYCLER_LAYOUT));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.onSaveInstanceState(outState);

        //Remember the layout the recycler view is in
        outState.putParcelable(SAVED_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();
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
        recyclerAdapter.addItems(data);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
