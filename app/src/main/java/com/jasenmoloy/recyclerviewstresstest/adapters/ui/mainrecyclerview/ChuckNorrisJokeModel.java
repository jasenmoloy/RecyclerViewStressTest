package com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview;

/**
 * Created by jasenmoloy on 8/24/16.
 */
public class ChuckNorrisJokeModel implements BaseModel {
    public String joke;

    public ChuckNorrisJokeModel(String joke) {
        this.joke = joke;
    }

    @Override
    public int getCardViewType() {
        return CardViewTypes.CHUCKNORRISJOKE;
    }
}
