package de.eiselecloud.glassplayer;

import de.eiselecloud.glassplayer.models.ShowDetailed;
import de.eiselecloud.glassplayer.models.ShowList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import de.eiselecloud.glassplayer.models.Episode;

/**
 * Created by alexander on 30.10.17.
 */

public interface GlassService {
    @GET("shows")
    Call<ShowList> getShows();

    @GET("shows/{id}")
    Call<ShowDetailed> getShow(@Path("id") int show_id);

    @GET("episodes/{id}")
    Call<Episode> getEpisode(@Path("id") int episode_id);
}
