package com.jasenmoloy.recyclerviewstresstest.domain.models.imgur;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jasenmoloy on 8/18/16.
 */
public class GalleryImageModel extends BaseGalleryResponseModel implements Parcelable {

    private static final Parcelable.Creator<GalleryImageModel> CREATOR = new Creator<GalleryImageModel>() {
        @Override
        public GalleryImageModel createFromParcel(Parcel parcel) {
            GalleryImageModel model = new GalleryImageModel();

            model.type = parcel.readString();
            model.animated = parcel.readInt() == 1;
            model.width = parcel.readInt();
            model.height = parcel.readInt();
            model.size = parcel.readInt();
            model.views = parcel.readInt();
            model.link = parcel.readString();
            model.gifv = parcel.readString();
            model.mp4 = parcel.readString();
            model.looping = parcel.readInt() == 1;

            return model;
        }

        @Override
        public GalleryImageModel[] newArray(int i) {
            return new GalleryImageModel[i];
        }
    };

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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeInt(animated? 1 : 0);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeInt(size);
        parcel.writeInt(views);
        parcel.writeString(link);
        parcel.writeString(gifv);
        parcel.writeString(mp4);
        parcel.writeInt(looping? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }
}
