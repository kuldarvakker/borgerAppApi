package com.qminder.borger.foursquare;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qminder.borger.app.domain.BurgerJoint;
import com.qminder.borger.foursquare.domain.BurgerJointOutput;
import com.qminder.borger.foursquare.domain.PhotoOutput;
import com.qminder.borger.utils.Utils;
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
    private String apiKey;
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public List<BurgerJoint> getTartuBurgerJoints() {

        List<BurgerJoint> result = new ArrayList<>();

        // category 13031 BurgerJoint
        Request request = new Request.Builder()
                .url("https://api.foursquare.com/v3/places/search?categories=13031&near=Tartu")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IllegalStateException("Response unsuccessful");
            }
            var entries =
                    gson.fromJson(response.body().string(), BurgerJointOutput.class)
                            .getResults();
            var burgerJoints = entries.stream()
                    .map(e -> {
                        var geocode = e.getGeocodes().getMain();
                        return new BurgerJoint(e.getFsq_id(), e.getName(), geocode.getLatitude(), geocode.getLongitude());
                    })
                    .collect(Collectors.toList());

            result.addAll(burgerJoints);

        } catch (IOException e) {
            throw new RuntimeException("service:IO_Error BurgerJoints.");
        } catch (IllegalStateException e) {
            throw new RuntimeException("service:Response_Error BurgerJoints.");
        }finally {
            Utils.close(response);
        }
        return result;
    }

    public List<String> getTartuBurgerJointPhotos(String fsqId) {

        Request request = new Request.Builder()
                .url("https://api.foursquare.com/v3/places/" + fsqId + "/photos?sort=NEWEST")
                .get()
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", apiKey)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IllegalStateException("Response unsuccessful");
            }
            Type photoOutputListType = new TypeToken<ArrayList<PhotoOutput>>(){}.getType();
            List<PhotoOutput> photos = gson.fromJson(response.body().string(), photoOutputListType);
            return photos.stream()
                    .map(photo -> photo.getPrefix() + "original" + photo.getSuffix())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("service:IO_Error BurgerJoint photos.");
        } catch (IllegalStateException e) {
            throw new RuntimeException("service:Response_Error BurgerJoint photos.");
        } finally {
            Utils.close(response);
        }
    }
}