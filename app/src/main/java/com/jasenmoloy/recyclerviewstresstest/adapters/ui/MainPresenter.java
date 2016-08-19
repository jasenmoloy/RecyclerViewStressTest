package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.os.Bundle;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public interface MainPresenter {

    void onCreate(Bundle savedInstanceBundle);
    void onSaveInstanceState(Bundle outState);
    void onStart();
    void onResume();
    void onStop();
    void onDestroy();
}
