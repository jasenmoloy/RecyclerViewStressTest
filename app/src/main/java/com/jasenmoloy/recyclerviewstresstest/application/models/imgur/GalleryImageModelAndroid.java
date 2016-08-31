package com.jasenmoloy.recyclerviewstresstest.application.models.imgur;

import android.os.Parcel;
import android.os.Parcelable;

import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;

/**
 * Created by jasenmoloy on 8/30/16.
 */
public class GalleryImageModelAndroid extends GalleryImageModel implements Parcelable {

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
