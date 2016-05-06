package com.epicodus.pilltracker;

//uses api documented at http://mapi-us.iterar.co/

public class Constants {
    public static final String OPEN_FDA_API_KEY = BuildConfig.OPEN_FDA_API_KEY;
    public static final String FIREBASE_URL = BuildConfig.FIREBASE_ROOT_URL;


    public static final String MAPI_API_BASE_URL = "http://mapi-us.iterar.co/api/";
    public static final String MAPI_SUBSTANCES_SEARCH = "substances.json";
    public static final String MAPI_AUTOCOMPLETE_TERM = "autocomplete";
    public static final String MAPI_QUERY_PARAMETER = "query";
    public static final String MAPI_DOSES_SEARCH = "doses.json";

    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";
    public static final String FIREBASE_URL_USERS = FIREBASE_URL + "/" + FIREBASE_LOCATION_USERS;
}
