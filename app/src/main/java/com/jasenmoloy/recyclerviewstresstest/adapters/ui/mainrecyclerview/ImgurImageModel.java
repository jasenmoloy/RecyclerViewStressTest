package com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview;

import android.net.Uri;

import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;

/**
 * Created by jasenmoloy on 8/24/16.
 */
public class ImgurImageModel implements BaseModel {
    public String title;
    public String type;
    public int fileSize;
    public Uri imageUri;
    public String description;

    public ImgurImageModel(GalleryImageModel model) {
        this.title = model.title;
        this.imageUri = Uri.parse(model.link);
        this.fileSize = model.size;
        this.description = model.description;
        this.type = model.type;
    }

    @Override
    public int getCardViewType() {
        return CardViewTypes.IMGURIMAGE;
    }
}
