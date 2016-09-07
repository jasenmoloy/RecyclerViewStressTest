package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.BaseModel;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.CardViewTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasenmoloy on 8/14/16.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<BaseModel> cardData;

    public MainRecyclerViewAdapter() {
        cardData = new ArrayList<>();
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
        ViewGroup viewGroup = null;
        ViewHolder holder = null;

        switch(viewType) {
            case CardViewTypes.CHUCKNORRISJOKE:
                viewGroup = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_chucknorrisjoke, parent, false);
                holder = new ChuckNorrisJokeCard(viewGroup);
                break;
            case CardViewTypes.IMGURIMAGE:
                viewGroup = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_imgurimage, parent, false);
                holder = new ImgurImageCard(viewGroup);
                break;
        }

        return holder;
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.onViewRecycled();
        super.onViewRecycled(holder);
    }

    public void addItem(BaseModel data) {
        cardData.add(data);
        notifyItemInserted(cardData.size()-1);
    }

    public void addItems(List<BaseModel> data) {
        for(BaseModel m : data) {
            cardData.add(m);
            notifyItemInserted(cardData.size()-1);
        }
    }


    public void replaceItems(List<BaseModel> data) {
        cardData = data;
        notifyDataSetChanged();
    }
}
