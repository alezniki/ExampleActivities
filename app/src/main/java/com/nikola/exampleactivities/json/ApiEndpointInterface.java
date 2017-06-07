package com.nikola.exampleactivities.json;

import com.nikola.exampleactivities.json.model.Event;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by nikola on 6/7/17.
 */

// Kasa koja mapira putanju servisa
// Opisuje koji metod koristimo ali i sta ocekujemo kao rezultat

// https://rest.bandsintown.com/artists/foo%20fighters/events?app_id=app_id%253Dtes

public interface ApiEndpointInterface {

    // Define the Endpoints
    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    @GET("artists/{name}/events")
    Call<List<Event>>getArtistByName(@Path("name") String artist, @QueryMap Map<String,String> options);



    /**
     * Endpoints are defined inside of an interface using special retrofit annotations
     * to encode details about the parameters and request method.
     * The return value is always a parameterized Call<T> object such as Call<User>
     *
     * */
}
