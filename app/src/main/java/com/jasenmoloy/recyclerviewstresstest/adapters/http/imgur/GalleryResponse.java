package com.jasenmoloy.recyclerviewstresstest.adapters.http.imgur;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jasenmoloy.recyclerviewstresstest.application.models.imgur.GalleryImageModelAndroid;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.BaseGalleryResponseModel;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryAlbumModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by jasenmoloy on 8/19/16.
 */
public class GalleryResponse {
    private JsonObject body;
    private Gson gson;

    public GalleryResponse(JsonObject body) {
        this.body = body;
        gson = new Gson();
    }

    public Collection<BaseGalleryResponseModel> getGalleryResponseArray() {
        JsonArray array = body.get("data").getAsJsonArray();
        List<BaseGalleryResponseModel> responses = new ArrayList<>(array.size());

        for(int i = 0; i < array.size(); i++) {

            if(array.get(i).getAsJsonObject().get("is_album").getAsBoolean()) {
                responses.add(gson.fromJson(array.get(i), GalleryAlbumModel.class));
            } else {
                responses.add(gson.fromJson(array.get(i), GalleryImageModelAndroid.class));
            }
        }

        return Collections.unmodifiableCollection(responses);
    }
}
