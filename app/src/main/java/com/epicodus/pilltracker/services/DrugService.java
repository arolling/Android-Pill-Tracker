package com.epicodus.pilltracker.services;


import android.util.Log;

import com.epicodus.pilltracker.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DrugService {
    public static final String TAG = DrugService.class.getSimpleName();
    private String MAPI_API_BASE_URL = Constants.MAPI_API_BASE_URL;
    private String MAPI_QUERY_PARAMETER = Constants.MAPI_QUERY_PARAMETER;
    private String MAPI_AUTOCOMPLETE_TERM = Constants.MAPI_AUTOCOMPLETE_TERM;
    private String MAPI_SUBSTANCES_SEARCH = Constants.MAPI_SUBSTANCES_SEARCH;
    private String MAPI_DOSES_SEARCH = Constants.MAPI_DOSES_SEARCH;

    public void autocompleteDrugs(String search, Callback callback){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(MAPI_API_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(MAPI_AUTOCOMPLETE_TERM);
        urlBuilder.addQueryParameter(MAPI_QUERY_PARAMETER, search);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "search url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<String> processAutocomplete(Response response){
        ArrayList<String> possibleDrugs = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject resultsJSON = new JSONObject(jsonData);
                JSONArray suggestionsJSON = resultsJSON.getJSONArray("suggestions");
                for(int i = 0; i < suggestionsJSON.length(); i++){
                    String drugName = suggestionsJSON.getString(i);
                    possibleDrugs.add(drugName);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return possibleDrugs;
    }
}
