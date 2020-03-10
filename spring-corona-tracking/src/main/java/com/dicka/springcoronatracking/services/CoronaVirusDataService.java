package com.dicka.springcoronatracking.services;


import com.dicka.springcoronatracking.models.LocationStats;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    //call csv file data corona virus data
    private static String CORONA_VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";
    private final OkHttpClient httpClient = new OkHttpClient();
    private List<LocationStats> locationStatsList = new ArrayList<>();

    public List<LocationStats> getAllStats(){
        return locationStatsList;
    }

    //@PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws Exception{
        List<LocationStats> newLocationStatsList = new ArrayList<>();
        Request request = new Request.Builder()
                .url(CORONA_VIRUS_DATA_URL)
                .build();

        StringReader csvBorderReader = null;
        try(Response response = httpClient.newCall(request).execute()){
            if (!response.isSuccessful()) throw new IOException("Warning...unexpected URI or code !!!! "+response);

            //get response body
            //System.out.println(response.body().string());
            csvBorderReader = new StringReader(response.body().string());
        }

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBorderReader);

        for (CSVRecord record : records){
            LocationStats locationStat = new LocationStats();
            //String state = record.get("Province/State");
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int lastestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(lastestCases);
            locationStat.setDiffFromPrevDay(lastestCases - prevDayCases);
            System.out.println(locationStat);
            newLocationStatsList.add(locationStat);
        }

        this.locationStatsList = newLocationStatsList;
    }
}
