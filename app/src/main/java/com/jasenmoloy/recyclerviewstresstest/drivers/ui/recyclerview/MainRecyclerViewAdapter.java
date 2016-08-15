package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.domain.BaseTestDataModel;

import java.util.List;

/**
 * Created by jasenmoloy on 8/14/16.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<BaseTestDataModel> cardData;

    public MainRecyclerViewAdapter(List<BaseTestDataModel> data) {
        cardData = data;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return cardData.get(position).getCardViewType();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setCard(cardData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return cardData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView;
        cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_image_description, parent, false);
        return new BasicViewHolder(cardView);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.onViewRecycled();
        super.onViewRecycled(holder);
    }
}
