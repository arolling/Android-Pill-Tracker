package com.epicodus.pilltracker.services;


import com.epicodus.pilltracker.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DrugService {
    public static final String TAG = DrugService.class.getSimpleName();

    public void findDrugs(String search, Callback callback){
        String MAPI_API_BASE_URL = Constants.MAPI_API_BASE_URL;
        String MAPI_QUERY_PARAMETER = Constants.MAPI_QUERY_PARAMETER;
        String MAPI_AUTOCOMPLETE_TERM = Constants.MAPI_AUTOCOMPLETE_TERM;
        String MAPI_SUBSTANCES_SEARCH = Constants.MAPI_SUBSTANCES_SEARCH;
        String MAPI_DOSES_SEARCH = Constants.MAPI_DOSES_SEARCH;

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(MAPI_API_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(MAPI_AUTOCOMPLETE_TERM);
        urlBuilder.addQueryParameter(MAPI_QUERY_PARAMETER, search);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        //Log.v(TAG, "search url: " + request);

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
