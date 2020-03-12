package com.dicka.springcoronatracking.services;

import com.dicka.springcoronatracking.models.SendData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class SendDataService {

    //send post data ke python service
    //uri http://localhost:5000/ --> service python

    private static final String SERVICE_PYTHON_API = "http://localhost:5000/";
    private final OkHttpClient httpClient = new OkHttpClient();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @PostConstruct
    public void sendData() throws IOException {
        SendData data = new SendData();
        data.setData("Bismillah");
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(data), JSON);
        Request request = new Request.Builder()
                .url(SERVICE_PYTHON_API + "save-computer")
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        //System.out.println("Status = "+jsonObject.get("status"));
        String status = jsonObject.get("status").toString();
        System.out.println("status = "+status);
        if (status.equals("409")){
            System.out.println("conflict save data");
        }else {
            System.out.println("success save data");
        }
    }
}
