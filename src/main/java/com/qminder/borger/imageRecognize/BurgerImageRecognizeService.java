package com.qminder.borger.imageRecognize;

import com.google.gson.Gson;
import com.qminder.borger.imageRecognize.domain.BurgerImageInput;
import com.qminder.borger.imageRecognize.domain.BurgerImageOutputSuccess;
import com.qminder.borger.utils.Utils;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BurgerImageRecognizeService {

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public String findFirstBurgerImage(List<String> pictureUrls) {
        var pojoInputObj = new BurgerImageInput();
        pojoInputObj.setUrls(pictureUrls);
        Request request = new Request.Builder()
                .url("https://pplkdijj76.execute-api.eu-west-1.amazonaws.com/prod/recognize")
                .post(RequestBody.create(MediaType.parse("JSON"), gson.toJson(pojoInputObj)))
                .addHeader("Accept", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                var s = gson.fromJson(response.body().string(), BurgerImageOutputSuccess.class);
                return s.getUrlWithBurger();
            }
        } catch (IOException e) {
            throw new RuntimeException("IO_Error finding first burger");
        } finally {
            Utils.close(response);
        }
        // no burger :(
        return "";
    }
}
