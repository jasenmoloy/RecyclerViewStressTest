package com.jasenmoloy.recyclerviewstresstest.adapters.ui;

import android.support.annotation.Nullable;
import android.util.Log;

import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jasenmoloy on 8/16/16.
 */
public class MainModel {
    private List<String> chuckNorrisJokes;
    private List<GalleryImageModel> images;

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

    public void setImgurCurrentPage(int imgurCurrentPage) {
        this.imgurCurrentPage = imgurCurrentPage;
    }

    public List<String> getChuckNorrisJokes() {
        return chuckNorrisJokes;
    }

    public List<GalleryImageModel> getImages() {
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

    public void addImages(List<GalleryImageModel> images) {
        Log.v("JAM", "Adding " + images.size() + " images to model!");

        for(GalleryImageModel m : images) {
            this.images.add(m);
        }
    }

    @Nullable
    public GalleryImageModel getNextImage() {
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
}
