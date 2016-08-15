package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.domain.BaseTestDataModel;

/**
 * Created by jasenmoloy on 8/14/16.
 */
class BasicViewHolder extends ViewHolder {
    TextView titleView;
    ImageView imageView;
    TextView descriptionView;

    public BasicViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.card_imagedescription_title);
        imageView = (ImageView) itemView.findViewById(R.id.card_imagedescription_image);
        descriptionView = (TextView) itemView.findViewById(R.id.card_imagedescription_description);
    }

    @Override
    void setCard(BaseTestDataModel data, int position) {
        titleView.setText(data.getTitle());
        //TODO: Set the image
        descriptionView.setText(data.getDescription());
    }

    @Override
    void onViewRecycled() {
        //TODO: Unload the image
    }
}
