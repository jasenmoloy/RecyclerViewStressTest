package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jasenmoloy on 8/14/16.
 */
abstract class ViewHolder <T> extends RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }

    abstract void setCard(T model, int position);

    abstract void onViewRecycled();
}
