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
    private static String ROOT_URL = "http://192.168.178.107:1234/api/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static GlassService getGlassService() {
        return getRetrofitInstance().create(GlassService.class);
    }
}

