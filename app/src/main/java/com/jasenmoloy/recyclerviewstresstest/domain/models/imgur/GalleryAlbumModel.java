package com.jasenmoloy.recyclerviewstresstest.domain.models.imgur;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jasenmoloy on 8/19/16.
 */
public class GalleryAlbumModel extends BaseGalleryResponseModel {
    @SerializedName("cover")
    public String cover;

    @SerializedName("cover_width")
    public int coverWidth;

    @SerializedName("cover_height")
    public int coverHeight;

    @SerializedName("account_url")
    public String accountUrl;

    @SerializedName("account_id")
    public int accountId;

    @SerializedName("privacy")
    public String privacy;

    @SerializedName("layout")
    public String layout;

    @SerializedName("views")
    public int views;

    @SerializedName("link")
    public String link;

    @SerializedName("ups")
    public int ups;

    @SerializedName("downs")
    public int downs;

    @SerializedName("points")
    public int points;

    @SerializedName("score")
    public int score;

    @SerializedName("vote")
    public String vote;

    @SerializedName("favorite")
    public boolean favorite;

    @SerializedName("nsfw")
    public boolean nsfw;

    @SerializedName("commentCount")
    public int commentCount;

    @SerializedName("topic")
    public String topic;

    @SerializedName("topicId")
    public String topicId;

    @SerializedName("imagesCount")
    public int imagesCount;

    @SerializedName("images")
    public GalleryImageModel[] images;
}
