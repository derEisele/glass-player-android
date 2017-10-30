package de.eiselecloud.glassplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by alexander on 30.10.17.
 */

public class Season{
    private ArrayList<Episode> episodes = new ArrayList<>();

    public ArrayList<Episode> getEpisodes(){
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    @SerializedName("season")
    @Expose
    private Integer season;

    public Integer getSeason() {
        return season;
    }

    private void setSeason(Integer season) {
        this.season = season;
    }
}