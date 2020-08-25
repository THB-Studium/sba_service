package com.example.demo.rest;

public class ApiConstants {

    public static final String API = "/api";
    
    // about the sessions:
    public static final String SESSIONS_COLLECTION = "/sessions";
    
    
    // about the functionality:
    
    public static final String USERS_COLLECTION = ApiConstants.API + "/users";
    public static final String USERS_ITEM = ApiConstants.USERS_COLLECTION + "/{userId}";
    
    public static final String BUECHER_COLLECTION = ApiConstants.API + "/buecher";
    public static final String BUECHER_ITEM = ApiConstants.BUECHER_COLLECTION + "/{buchId}";
    
    public static final String RESERVIRUNGEN_COLLECTION = ApiConstants.API + "/reservierungen";
    public static final String RESERVIRUNGEN_ITEM = ApiConstants.RESERVIRUNGEN_COLLECTION + "/{reservierungId}";
    
    public static final String AUSLEIHE_COLLECTION = ApiConstants.API + "/ausleihe";
    public static final String AUSLEIHE_ITEM = ApiConstants.AUSLEIHE_COLLECTION + "/{ausleiheId}";
    
    public static final String WEITERLEITUNGEN_COLLECTION = ApiConstants.API + "/weiterleitungen";
    public static final String WEITERLEITUNGEN_ITEM = ApiConstants.WEITERLEITUNGEN_COLLECTION + "/{weiterleitungId}";
    
    public static final String FAVORIES_COLLECTION = ApiConstants.API + "/favories";
    public static final String FAVORIES_ITEM = ApiConstants.FAVORIES_COLLECTION + "/{favoryId}";    
    
    public static final String MAHNUNG_COLLECTION = ApiConstants.API + "/mahnungen";
    public static final String MAHNUNG_ITEM = ApiConstants.FAVORIES_COLLECTION + "/{mahnungId}";  
    
}
