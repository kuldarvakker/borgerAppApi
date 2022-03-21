package com.qminder.borger.foursquare;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qminder.borger.foursquare.domain.PhotoOutput;
import com.qminder.borger.imageRecognize.domain.BurgerImageOutputSuccess;
import lombok.Builder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoursquareService {

    @Value("${foursquare.apiKey}")
    private final String apiKey;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public String getTartuBurgerJoints() {
        // TODO
        return "";
    }

    public List<String> getTartuBurgerJointPhotos(String fsqId) {

        Request request = new Request.Builder()
                .url("https://api.foursquare.com/v3/places/" + fsqId + "/photos?sort=NEWEST")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .build();
        try {
            var response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Type photoOutputListType = new TypeToken<ArrayList<PhotoOutput>>(){}.getType();
                List<PhotoOutput> photos = gson.fromJson(response.body().string(), photoOutputListType);
                return photos.stream()
                        .map(photo -> photo.prefix + "original" + photo.suffix)
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error with BurgerJoint photos.");
        }

        return new ArrayList<>();
    }

}
