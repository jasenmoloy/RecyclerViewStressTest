package com.jasenmoloy.recyclerviewstresstest.drivers.database.retrofit.imgur;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.BaseGalleryResponseModel;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryAlbumModel;
import com.jasenmoloy.recyclerviewstresstest.domain.models.imgur.GalleryImageModel;

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
        JsonParser parser = new JsonParser();
        JsonArray array = body.get("data").getAsJsonArray();
        List<BaseGalleryResponseModel> responses = new ArrayList<>(array.size());

        for(int i = 0; i < array.size(); i++) {

            if(array.get(i).getAsJsonObject().get("is_album").getAsBoolean()) {
                responses.add(gson.fromJson(array.get(i), GalleryAlbumModel.class));
            } else {
                responses.add(gson.fromJson(array.get(i), GalleryImageModel.class));
            }
        }

        return Collections.unmodifiableCollection(responses);
    }
}
