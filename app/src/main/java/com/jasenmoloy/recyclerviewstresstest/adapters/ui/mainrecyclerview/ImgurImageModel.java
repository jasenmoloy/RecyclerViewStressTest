package com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview;

import android.net.Uri;

/**
 * Created by jasenmoloy on 8/24/16.
 */
public class ImgurImageModel implements BaseModel {
    public String title;
    public String type;
    public int fileSize;
    public Uri imageUri;
    public String description;

    public ImgurImageModel(String title, Uri imageUri, String type, int size, String description) {
        this.title = title;
        this.imageUri = imageUri;
        this.fileSize = size;
        this.description = description;

        this.type = type;
    }

    @Override
    public int getCardViewType() {
        return CardViewTypes.IMGURIMAGE;
    }
}
