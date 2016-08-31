package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.jasenmoloy.recyclerviewstresstest.application.models.imgur.GalleryImageModelAndroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainModel {
    private static final String SAVED_JOKES_LIST = "model_jokes";
    private static final String SAVED_IMAGES_LIST = "model_images";
    private static final String SAVED_CURRENT_PAGE = "model_current_page";

    private ArrayList<String> chuckNorrisJokes;
    private ArrayList<GalleryImageModelAndroid> images;

    private int jokesIdx = 0;
    private int imagesIdx = 0;

    private int imgurCurrentPage = 0;

    public MainModel() {
        chuckNorrisJokes = new ArrayList<>();
        images = new ArrayList<>();

        jokesIdx = 0;
        imagesIdx = 0;
        imgurCurrentPage = 0;
    }

    public MainModel(Bundle instanceState) {
        chuckNorrisJokes = instanceState.getStringArrayList(SAVED_JOKES_LIST);
        images = instanceState.getParcelableArrayList(SAVED_IMAGES_LIST);
        imgurCurrentPage = instanceState.getInt(SAVED_CURRENT_PAGE);

        jokesIdx = 0;
        imagesIdx = 0;
    }

    public void setImgurCurrentPage(int imgurCurrentPage) {
        this.imgurCurrentPage = imgurCurrentPage;
    }

    public List<String> getChuckNorrisJokes() {
        return chuckNorrisJokes;
    }

    public List<GalleryImageModelAndroid> getImages() {
        return images;
    }

    public int getImgurCurrentPage() {
        return imgurCurrentPage;
    }

    public void addChuckNorrisJokes(List<String> chuckNorrisJokes) {
        Log.v("JAM", "Adding " + chuckNorrisJokes.size() + " jokes to model!");

        for(String s : chuckNorrisJokes) {
            this.chuckNorrisJokes.add(s);
        }
    }

    public void addImages(List<GalleryImageModelAndroid> images) {
        Log.v("JAM", "Adding " + images.size() + " images to model!");

        for(GalleryImageModelAndroid m : images) {
            this.images.add(m);
        }
    }

    @Nullable
    public GalleryImageModelAndroid getNextImage() {
        if(imagesIdx >= images.size())
            return null;

        return images.get(imagesIdx++);
    }

    @Nullable
    public String getNextJoke() {
        if(jokesIdx >= chuckNorrisJokes.size())
            return null;

        return chuckNorrisJokes.get(jokesIdx++);
    }

    public Bundle onSaveInstanceState() {
        Bundle outBundle = new Bundle();

        outBundle.putStringArrayList(SAVED_JOKES_LIST, chuckNorrisJokes);
        outBundle.putParcelableArrayList(SAVED_IMAGES_LIST, images);
        outBundle.putInt(SAVED_CURRENT_PAGE, imgurCurrentPage);

        return outBundle;
    }
}
