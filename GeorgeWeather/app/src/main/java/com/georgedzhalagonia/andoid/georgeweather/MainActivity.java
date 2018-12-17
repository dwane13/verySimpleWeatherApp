package com.georgedzhalagonia.andoid.georgeweather;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    static TextView placeTV;
    static TextView temperatureTV;
    static TextView descriptionTV;
    static int MY_PERMISSIONS_REQUEST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Some expressions
        placeTV = findViewById(R.id.show_place);
        temperatureTV = findViewById(R.id.show_weather);
        descriptionTV = findViewById(R.id.show_description);

        // Check and ask user for permission (GPS)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST

            );

            return;
        }

        //Get last knows user place
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);

        Location location = locationManager.getLastKnownLocation(provider);
        // Get double values and parsing in to integer
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        int lati = (int) lat;
        int lngt = (int) lng;


        // Simple dt with my API from open weather.
        DownloadTask dt = new DownloadTask();
        dt.execute("https://api.openweathermap.org/data/2.5/weather?lat="+lati+"&lon="+lngt+"&appid=3325725f20e0429401fa16c609e888ae");
    }
}
