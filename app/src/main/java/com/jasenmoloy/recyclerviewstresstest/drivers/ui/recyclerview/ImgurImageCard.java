package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ImgurImageModel;

/**
 * Created by jasenmoloy on 8/24/16.
 */
class ImgurImageCard extends ViewHolder<ImgurImageModel> {
    TextView title;
    ImageView image;
    TextView description;

    public ImgurImageCard(View itemView) {
        super(itemView);

        this.title = (TextView) itemView.findViewById(R.id.cardview_imgurimage_title);
        this.image = (ImageView) itemView.findViewById(R.id.cardview_imgur_imageview);
        this.description = (TextView) itemView.findViewById(R.id.cardview_imgur_description);
    }

    @Override
    void setCard(ImgurImageModel model, int position) {
        title.setText(model.title);

        Glide.with(image.getContext()).load(model.imageUri).into(image);

        description.setText(model.description);
    }

    @Override
    void onViewRecycled() {
        Glide.clear(image);
        image.setImageDrawable(null);
    }
}
