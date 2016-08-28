package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.util.Log;

import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainModelImpl {
    private List<String> chuckNorrisJokes;
    private List<GalleryImageModel> images;

    public MainModelImpl() {
        chuckNorrisJokes = new ArrayList<>();
        images = new ArrayList<>();
    }

    public void setChuckNorrisJokes(List<String> chuckNorrisJokes) {
        Log.v("JAM", "Adding Jokes to model!");
        this.chuckNorrisJokes = chuckNorrisJokes;
    }

    public void setImages(List<GalleryImageModel> images) {
        Log.v("JAM", "Adding Images to model!");
        this.images = images;
    }

    public List<String> getChuckNorrisJokes() {
        return chuckNorrisJokes;
    }

    public List<GalleryImageModel> getImages() {
        return images;
    }
}
