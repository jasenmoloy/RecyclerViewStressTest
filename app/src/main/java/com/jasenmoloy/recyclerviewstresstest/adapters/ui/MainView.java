package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;

import java.util.List;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public interface MainView {
    void onDataItemLoaded(BaseModel data);
    void onDataItemsLoaded(List<BaseModel> data);

    void showToast(String message);
}
