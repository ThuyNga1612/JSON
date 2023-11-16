package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Continents> mang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listviewContinents);
        mang = new ArrayList<Continents>();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new docJSON().execute("https://gist.githubusercontent.com/ThuyNga1612/7a9489eea61a23b98774c0d2450ddd6d/raw/0ccee841390aa7e891743ce01c228f7f55acad85/Continents.txt");
            }
        });
    }

    class docJSON extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                JSONArray mangjson = root.getJSONArray("QuocGia");
                for(int i=0; i<mangjson.length(); i++){
                    JSONObject qg = mangjson.getJSONObject(i);
                    mang.add( new Continents(
                            qg.getString("ten"),
                            qg.getString("mota"),
                            qg.getString("hinhanh")
                    ));
                }
                ListAdapter adapter = new ListAdapter(
                        getApplicationContext(),
                        R.layout.activity_quoc_gia,
                        mang
                );
                lv.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String docNoiDung_Tu_URL(String theUrl) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(theUrl);
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line + "\n");
                }
                bufferedReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
}