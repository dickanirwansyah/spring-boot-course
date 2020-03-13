package com.dicka.springcoronatracking.services;

import com.dicka.springcoronatracking.model.Person;
import com.dicka.springcoronatracking.models.SendData;
import com.google.gson.Gson;
import okhttp3.*;
import org.json.JSONArray;
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

    //example URI json from https://randomuser.me/api/
    private static final String SERVICE_RANDOM_USER = "https://randomuser.me/api/";

    //example json array
    private static final String jsonArray =   "{"
            + "  \"geodata\": ["
            + "    {"
            + "      \"id\": \"1\","
            + "      \"name\": \"Julie Sherman\","
            + "      \"gender\" : \"female\","
            + "      \"latitude\" : \"37.33774833333334\","
            + "      \"longitude\" : \"-121.88670166666667\""
            + "    },"
            + "    {"
            + "      \"id\": \"2\","
            + "      \"name\": \"Johnny Depp\","
            + "      \"gender\" : \"male\","
            + "      \"latitude\" : \"37.336453\","
            + "      \"longitude\" : \"-121.884985\""
            + "    }"
            + "  ]"
            + "}";

    //@PostConstruct
    public void sendData() throws IOException {
        SendData data = new SendData();
        data.setData("Bismillah");
        data.setData1("coba data 1");
        data.setData2("coba data 2");
        String[] datas = {"test", "test", "test"};
        data.setTheDatas(datas);
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(gson.toJson(data), JSON);
        System.out.println("JSON SENDER = "+gson.toJson(data));
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

    //@PostConstruct
    public void parseingJsonArrayRandomUser() throws IOException{
        Request request = new Request.Builder()
                .url(SERVICE_RANDOM_USER)
                .build();

        try(Response response = httpClient.newCall(request).execute()){
            if(!response.isSuccessful()) throw new IOException("error !");
            //System.out.println(response.body().string());
            final JSONObject obj = new JSONObject(response.body().string());
            final JSONArray results = obj.getJSONArray("results");
            //System.out.println(results);
            final int n = results.length();
            for (int i=0; i < n; i++){
                final JSONObject objectPerson = results.getJSONObject(i);
                String gender = objectPerson.getString("gender");

                //object name
                String nameOfTitle = objectPerson.getJSONObject("name").getString("title");
                String nameOfFirst = objectPerson.getJSONObject("name").getString("first");
                String nameOfLast = objectPerson.getJSONObject("name").getString("last");

                //location
                int locationStreetNumber = objectPerson.getJSONObject("location").getJSONObject("street").getInt("number");
                String locationStreetName = objectPerson.getJSONObject("location").getJSONObject("street").getString("name");
                String locationCity = objectPerson.getJSONObject("location").getString("city");

                //print data
                System.out.println("GENDER : "+gender);
                System.out.println("NAME OF TITLE : "+nameOfTitle);
                System.out.println("NAME OF FIRST : "+nameOfFirst);
                System.out.println("NAME OF LAST : "+nameOfLast);
                System.out.println("LOCATION STREET NUMBER : "+locationStreetNumber);
                System.out.println("LOCATION STREET NAME : "+locationStreetName);
                System.out.println("LOCATION CITY : "+locationCity);
            }
        }
    }

    //@PostConstruct
    public void parsingJsonArray(){
        final JSONObject obj = new JSONObject(jsonArray);
        final JSONArray geoDataArray = obj.getJSONArray("geodata");
        final int n = geoDataArray.length();
        for (int i=0; i < n; i++){
            final JSONObject objectPerson = geoDataArray.getJSONObject(i);
            Person person = new Person();
            person.setId(objectPerson.getInt("id"));
            person.setName(objectPerson.getString("name"));
            person.setGender(objectPerson.getString("gender"));
            person.setLatitude(objectPerson.getDouble("latitude"));
            person.setLongitude(objectPerson.getDouble("longitude"));
            System.out.println(person);
        }
    }
}
