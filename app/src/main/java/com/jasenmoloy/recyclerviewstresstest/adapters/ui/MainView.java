package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.content.Context;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public interface MainView {
    interface CardData {
        String getDescription();
        int getCardViewType();
    }

    void onDataItemLoaded(CardData data);

    void showToast(String message);

    Context getContext();
}
