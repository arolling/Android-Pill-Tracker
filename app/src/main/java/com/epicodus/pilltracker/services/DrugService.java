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
        } catch (JSONException j) {
            j.printStackTrace();
        }
        return possibleDrugs;
    }

    public void findIngredients(String brand, Callback callback){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(MAPI_API_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(brand);
        urlBuilder.addPathSegment(MAPI_SUBSTANCES_SEARCH);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "substances url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<String> processIngredients(Response response){
        ArrayList<String> ingredients = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONArray substancesJSON = new JSONArray(jsonData);
                for(int i = 0; i < substancesJSON.length(); i++){
                    String ingredient = substancesJSON.getString(i);
                    //Log.v("molecule: ", ingredient);
                    ingredients.add(ingredient);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();
        }
        return ingredients;
    }

    public void findStrengths(String drug, Callback callback){
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(MAPI_API_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(drug);
        urlBuilder.addPathSegment(MAPI_DOSES_SEARCH);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Log.v(TAG, "doses url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<String> processStrengths(Response response){
        ArrayList<String> strengths = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONArray dosagesJSON = new JSONArray(jsonData);
                for(int i = 0; i < dosagesJSON.length(); i++){
                    String dose = dosagesJSON.getString(i);
                    strengths.add(dose);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException j){
            j.printStackTrace();
        }
        return strengths;
    }
}
