package com.jasenmoloy.recyclerviewstresstest.domain.models.imgur;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jasenmoloy on 8/18/16.
 */
public class GalleryImageModel extends BaseGalleryResponseModel {
    @SerializedName("type")
    public String type;

    @SerializedName("animated")
    public boolean animated;

    @SerializedName("width")
    public int width;

    @SerializedName("height")
    public int height;

    @SerializedName("size")
    public int size; //Size of the image in bytes

    @SerializedName("views")
    public int views; //Number of image views

    @SerializedName("link")
    public String link;

    @SerializedName("gifv")
    public String gifv;

    @SerializedName("mp4")
    public String mp4;

    @SerializedName("looping")
    public boolean looping;
}
