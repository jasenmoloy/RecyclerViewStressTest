package com.jasenmoloy.recyclerviewstresstest.domain;

/**
 * Created by jasenmoloy on 8/14/16.
 */
public class BaseTestDataModel {

    public static final int DEFAULT_CARDVIEW = 0;

    String description;
    String title;


    public BaseTestDataModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getCardViewType() {
        return DEFAULT_CARDVIEW;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
