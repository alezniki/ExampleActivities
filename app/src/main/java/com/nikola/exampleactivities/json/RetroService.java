package com.nikola.exampleactivities.json;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nikola on 6/7/17.
 */

public class RetroService {

    public static final String BASE_URL = "https://rest.bandsintown.com/";


    public static Retrofit getRetrofitInstance(){

        // Retrofit instanca preko koje ce ici komunikacija
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

    // Definisemo konkretnu instancu servisa na intnerntu sa kojim vrsimo komunikaciju
    public static  ApiEndpointInterface apiEndpointInterface(){
        ApiEndpointInterface apiService = getRetrofitInstance().create(ApiEndpointInterface.class);

        return apiService;

    }

}
