package de.eiselecloud.glassplayer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexander on 30.10.17.
 */

public class RetroClient {

    /********
     * URLS
     *******/


    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance(String baseURL) {

        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static GlassService getGlassService(String baseURL) {
        return getRetrofitInstance(baseURL).create(GlassService.class);
    }
}

