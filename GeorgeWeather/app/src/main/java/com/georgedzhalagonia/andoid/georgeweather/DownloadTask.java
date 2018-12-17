package com.georgedzhalagonia.andoid.georgeweather;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... urls) {

        // Just basic work with JSON
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = reader.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            reader.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // Getting info from our JSON
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONObject details = jsonObject.getJSONArray("weather").getJSONObject(0);
            JSONObject main = jsonObject.getJSONObject("main");
            String weatherDesc = details.getString("description");
            String place = jsonObject.getString("name");
            double temperature = Double.parseDouble(main.getString("temp"));
            int tempInInteger = (int) (temperature - 273.5);

            // Apply to the TextView
            MainActivity.temperatureTV.setText(String.valueOf(+tempInInteger+"°C"));
            MainActivity.placeTV.setText("District: " + place);
            MainActivity.descriptionTV.setText("Description: " + weatherDesc);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
