package com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview;

import android.net.Uri;

/**
 * Created by jasenmoloy on 8/24/16.
 */
public class ImgurImageModel implements BaseModel {
    public String title;
    public Uri imageUri;
    public String description;

    public ImgurImageModel(String title, Uri imageUri, String description) {
        this.title = title;
        this.imageUri = imageUri;
        this.description = description;
    }

    @Override
    public int getCardViewType() {
        return CardViewTypes.IMGURIMAGE;
    }
}
