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
    String answer;
    // да, да, потом сделаю декомпозицию кода
    // бе
    public void Read() throws InterruptedException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        DataReading = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("GosAgent", "start... ");
                    String url = Configs.dbHost + "?action=select&table=" + Configs.tableName + PluginCore.markerType;

                    connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                    connection.setDoInput(true);
                    connection.connect();

                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                }

                Log.d("GosAgent", "+ FoneService успешное подключение");

                try {
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is, StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    String bfr_st;
                    while ((bfr_st = br.readLine()) != null) {
                        sb.append(bfr_st);
                    }

                    Log.d("GosAgent", "+ FoneService - полный ответ сервера:\n"
                            + sb);

                    answer = sb.toString();
                    answer = answer.substring(0, answer.indexOf("]") + 1);

                    is.close();
                    br.close();

                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                } finally {
                    connection.disconnect();
                    Log.d("GosAgent",
                            "+ FoneService --------------- ЗАКРОЕМ СОЕДИНЕНИЕ");
                }

                try {
                    JSONArray ja = new JSONArray(answer);
                    JSONObject jo;

                    int i = 0;

                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);

                        PluginCore.addLotData(jo.getInt("id"), jo.getDouble("coordinatesX"), jo.getDouble("coordinatesY"));

                        i++;

                    }
                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                }
            }
        });

        DataReading.run();

    }

    public void ReadAllLot(String x, String y) throws InterruptedException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        DataReading = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("GosAgent", "start... ");
                    String url = Configs.dbHost + "?action=getData&table=" + Configs.tableName + PluginCore.markerType +
                            "&X=" + x + "&Y=" + y;

                    connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setReadTimeout(10000);
                    connection.setConnectTimeout(15000);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                    connection.setDoInput(true);
                    connection.connect();

                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                }

                Log.d("GosAgent", "+ FoneService успешное подключение");

                try {
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is, StandardCharsets.UTF_8));
                    StringBuilder sb = new StringBuilder();
                    String bfr_st;
                    while ((bfr_st = br.readLine()) != null) {
                        sb.append(bfr_st);
                    }

                    Log.d("GosAgent", "+ FoneService - полный ответ сервера:\n"
                            + sb);

                    answer = sb.toString();
                    answer = answer.substring(0, answer.indexOf("]") + 1);

                    is.close();
                    br.close();

                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                } finally {
                    connection.disconnect();
                    Log.d("GosAgent",
                            "+ FoneService --------------- ЗАКРОЕМ СОЕДИНЕНИЕ");
                }

                try {
                    JSONArray ja = new JSONArray(answer);
                    JSONObject jo;

                    int i = 0;

                    while (i < ja.length()) {
                        jo = ja.getJSONObject(i);

                        List<String> results = new ArrayList<>();
                        for (String tag : AllLotData.getTeg()) {
                            String result = null;

                            try {
                                result = jo.getString(tag);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (result != null && result.length() > 1)
                                results.add(result);
                        }

                        Log.d("GosAgent","success parse data");

                        AllLotData dataTag = new AllLotData(results);
                        PluginCore.allLotData.add(dataTag);

                        i++;

                    }
                } catch (Exception e) {
                    Log.d("GosAgent", "+ FoneService ошибка: " + e.getMessage());
                }
            }
        });

        DataReading.run();
    }

}