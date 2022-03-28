package com.example.exchangeratesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Valute> valutes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView currencyList = findViewById(R.id.list_currency);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        currencyList.setLayoutManager(layoutManager);

        Button btnRun = findViewById(R.id.btn_run);
        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CurrencyAdapter adapter = new CurrencyAdapter(MainActivity.this, valutes);
                currencyList.setAdapter(adapter);
                String url = "https://www.cbr-xml-daily.ru/daily_json.js";
                new GetURLData().execute(url);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private class GetURLData extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray jsonArray = jsonObj.getJSONObject("Valute").names();
                for (int i = 0; i < (jsonArray != null ? jsonArray.length() : 0); i++) {
                    String jsonName = jsonArray.getString(i);
                    String name = jsonObj.getJSONObject("Valute").getJSONObject(jsonName).getString("Name");
                    double value = jsonObj.getJSONObject("Valute").getJSONObject(jsonName).getDouble("Value");
                    valutes.add(new Valute(jsonName, name, String.valueOf(value)));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}