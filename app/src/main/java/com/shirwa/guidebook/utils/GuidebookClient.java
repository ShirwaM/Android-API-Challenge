package com.shirwa.guidebook.utils;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by shirwamohamed on 1/20/15.
 */
public class GuidebookClient {
    private HttpClient mClient;
    final String URL = "http://guidebook.com/service/v2/upcomingGuides/";
    public String TAG = "Guidebook App";

    public GuidebookClient() {
        mClient = new DefaultHttpClient();
    }

    public ArrayList<Guide> getData() {
        HttpGet getRequest = new HttpGet(URL);
        InputStream inputStream = null;
        ArrayList<Guide> list = new ArrayList<>();
        try {
            HttpResponse response = mClient.execute(getRequest);
            inputStream = response.getEntity().getContent();
            if (inputStream != null) {
                JSONObject objectDump = new JSONObject(getStringInputeSteam(inputStream));
                JSONArray jsonArray = objectDump.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++)
                    list.add(new Guide(jsonArray.getJSONObject(i)));
                Log.v(TAG, jsonArray.toString());
            } else {
                Log.v(TAG, "Input is empty...");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error! " + e.getMessage());
        }
        return list;
    }

    private static String getStringInputeSteam(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;

    }
}
