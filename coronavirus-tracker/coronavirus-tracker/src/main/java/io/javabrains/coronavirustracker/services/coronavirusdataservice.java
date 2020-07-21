package io.javabrains.coronavirustracker.services;


import io.javabrains.coronavirustracker.Modules.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service

public class coronavirusdataservice {

    List<LocationStats> allist = new ArrayList<>();
    private static String url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    public List<LocationStats> getAllist() {
        return allist;
    }

    public int getSum() {
        return sum;
    }

    public int getNewsum() {
        return newsum;
    }

    int sum=0;
    int newsum=0;

    @PostConstruct


    public void fecthvirusdata() throws IOException, InterruptedException {


        List<LocationStats> list = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvBodyReader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);

        for (CSVRecord record : records)
        {
            LocationStats locationstat = new LocationStats();
            locationstat.setState(record.get("Province/State"));
            locationstat.setCountry(record.get("Country/Region"));
            locationstat.setLatestcases(Integer.parseInt(record.get(record.size()-1)));
            sum=sum+Integer.parseInt(record.get(record.size()-1));
            newsum=newsum+Math.abs(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
            locationstat.setDelta(Math.abs(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2))));
            list.add(locationstat);
        }

        this.allist=list;
    }

}
