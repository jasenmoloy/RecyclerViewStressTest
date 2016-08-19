package com.jasenmoloy.recyclerviewstresstest.domain.models.imgur;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jasenmoloy on 8/19/16.
 */
public class BaseGalleryResponseModel {
    @SerializedName("id")
    public String id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("datetime")
    public int datetime;

    @SerializedName("is_album")
    public boolean isAlbum;
}
