package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.MainView;

/**
 * Created by jasenmoloy on 8/14/16.
 */
class BasicViewHolder extends ViewHolder {
    ImageView imageView;
    TextView descriptionView;

    public BasicViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.card_imagedescription_image);
        descriptionView = (TextView) itemView.findViewById(R.id.card_imagedescription_description);
    }

    @Override
    void setCard(MainView.CardData data, int position) {
        //TODO: Set the image
        descriptionView.setText(data.getDescription());
    }

    @Override
    void onViewRecycled() {
        //TODO: Unload the image
    }
}
