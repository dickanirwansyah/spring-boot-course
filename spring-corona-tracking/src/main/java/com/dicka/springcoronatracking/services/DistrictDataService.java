package com.dicka.springcoronatracking.services;

import com.dicka.springcoronatracking.models.District;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistrictDataService {

    private static String DISTRICT_DATA_URI_CSV = "https://raw.githubusercontent.com/edwardsamuel/Wilayah-Administratif-Indonesia/master/csv/districts.csv";
    private List<District> districtList = new ArrayList<>();
    private final OkHttpClient httpClient = new OkHttpClient();

    @PostConstruct
    public void fetchingDataDistrict() throws Exception{

        Request request = new Request.Builder()
                .url(DISTRICT_DATA_URI_CSV)
                .build();

        StringReader strDistrictReader = null;
        try(Response response = httpClient.newCall(request).execute()){
            if (!response.isSuccessful()) throw new IOException("failed");
            //System.out.println(response.body().string());
            strDistrictReader = new StringReader(response.body().string());
        }

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(strDistrictReader);
        for (CSVRecord record : records){
            String idDistrict = record.get("1101010");
            String codeDistrict = record.get("1101");
            String nameDistrict = record.get("TEUPAH SELATAN");
            System.out.println(idDistrict + " - "+codeDistrict+" - "+nameDistrict);
        }
    }
}
