package com.example.gosagent;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReadData{

    public HttpURLConnection connection;
    Thread DataReading;
    String answerDB;

    public void GetMarkersData() throws InterruptedException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataReading = new Thread(new Runnable() {
            @Override
            public void run() {
                String typeConnection = ConfigsForDB.dbHost + "?action=select&table=" + ConfigsForDB.tableName + PluginCore.MarkerType;
                if (!CanGetData(typeConnection))
                    return;

                ParseMarkersData();
            }
        });

        DataReading.run();
    }

    public void GetLotData(String x, String y) throws InterruptedException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataReading = new Thread(new Runnable() {
            @Override
            public void run() {
                String typeConnection = ConfigsForDB.dbHost + "?action=getData&table=" + PluginCore.getTableName() +
                        "&X=" + x + "&Y=" + y;
                if (!CanGetData(typeConnection))
                    return;

                ParseLotInformation();
            }
        });

        DataReading.run();
    }

    private boolean CanGetData(String typeConnection) {
        if (!SetConnection(typeConnection))
            return false;

        return GetServerAnswer();
    }

    private boolean SetConnection(String typeConnection) {
        try {
            Log.d("GosAgent", "start connection");

            connection = (HttpURLConnection) new URL(typeConnection).openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setDoInput(true);

            connection.connect();

            Log.d("GosAgent", "successful connection");
        } catch (Exception e) {
            Log.d("GosAgent", "error connection: " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean GetServerAnswer() {
        try {
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder answerDB = new StringBuilder();

            String getLotInformation = bufferedReader.readLine();

            while (getLotInformation != null) {
                answerDB.append(getLotInformation);
                getLotInformation = bufferedReader.readLine();
            }

            Log.d("GosAgent", "Successful get information");

            this.answerDB = answerDB.toString();
            this.answerDB = this.answerDB.substring(0, this.answerDB.indexOf("]") + 1);

            inputStream.close();
            bufferedReader.close();

        } catch (Exception e) {
            Log.d("GosAgent", "error get information " + e.getMessage());
            return false;
        } finally {
            connection.disconnect();
            Log.d("GosAgent", "connection closed");
        }
        return true;
    }

    private void ParseMarkersData() {
        try {
            JSONArray JSON_All_Data = new JSONArray(answerDB);
            JSONObject JSON_data;

            int attributeNumber = 0;

            while (attributeNumber < JSON_All_Data.length()) {
                JSON_data = JSON_All_Data.getJSONObject(attributeNumber);
                PluginCore.addLotData(JSON_data.getInt("id"), JSON_data.getDouble("coordinatesX"),
                        JSON_data.getDouble("coordinatesY"));
                attributeNumber++;
            }
        } catch (Exception e) {
            Log.d("GosAgent", "fatal error " + e.getMessage());
        }
    }

    private void ParseLotInformation() {
        try {
            JSONArray JSON_All_Data = new JSONArray(answerDB);
            JSONObject JSON_data;

            int attributeNumber = 0;

            while (attributeNumber < JSON_All_Data.length()) {
                JSON_data = JSON_All_Data.getJSONObject(attributeNumber);

                PluginCore.LotInformationData.add(ParseAttributeInformation(JSON_data));

                Log.d("GosAgent","success parse information lot");

                attributeNumber++;
            }
        } catch (Exception errorMessage) {
            Log.d("GosAgent", "fatal error " + errorMessage.getMessage());
        }
    }

    private LotInformationsData ParseAttributeInformation(JSONObject JSON_data) {
        LotInformationsData allAttributeInformation = new LotInformationsData();
        List<String> description = new ArrayList<>();

        for (String tag : LotInformationsData.getTeg()) {
            String attributeInformation = null;
            try {
                attributeInformation = JSON_data.getString(tag);

                if (attributeInformation == null || attributeInformation.length() <= 1)
                    continue;

                switch (tag) {
                    case "number":          allAttributeInformation.numberLot = attributeInformation; break;
                    case "link":            allAttributeInformation.linkLot = attributeInformation; break;
                    case "location":        allAttributeInformation.locationLot = attributeInformation; break;
                    case "monthly_payment": allAttributeInformation.monthlyPaymentLot = attributeInformation; break;
                    case "initial_price":   allAttributeInformation.initialPriceLot = attributeInformation; break;
                    default:                description.add(attributeInformation); break;
                }

            } catch (JSONException errorMessage) {
                errorMessage.printStackTrace();
            }
        }

        allAttributeInformation.lotConfigs.addAll(description);
        return allAttributeInformation;
    }
}