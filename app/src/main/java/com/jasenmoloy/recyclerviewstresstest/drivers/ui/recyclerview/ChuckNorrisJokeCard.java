package com.jasenmoloy.recyclerviewstresstest.drivers.ui.recyclerview;

import android.view.View;
import android.widget.TextView;

import com.jasenmoloy.recyclerviewstresstest.R;
import com.jasenmoloy.recyclerviewstresstest.adapters.ui.mainrecyclerview.ChuckNorrisJokeModel;

/**
 * Created by jasenmoloy on 8/14/16.
 */
class ChuckNorrisJokeCard extends ViewHolder<ChuckNorrisJokeModel> {
    TextView jokeTextView;


    public ChuckNorrisJokeCard(View itemView) {
        super(itemView);

        jokeTextView = (TextView) itemView.findViewById(R.id.cardview_chucknorrisjoke_joke);
    }

    @Override
    void setCard(ChuckNorrisJokeModel model, int position) {
        jokeTextView.setText(model.joke);
    }

    @Override
    void onViewRecycled() {
        //TODO: Unload the image.
    }
}
