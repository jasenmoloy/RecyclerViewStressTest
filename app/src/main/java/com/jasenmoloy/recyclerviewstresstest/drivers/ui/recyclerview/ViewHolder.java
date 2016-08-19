package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jasenmoloy.recyclerviewstresstest.adapters.ui.MainView;

/**
 * Created by jasenmoloy on 8/14/16.
 */
abstract class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    abstract void setCard(MainView.CardData data, int position);

    abstract void onViewRecycled();
}
