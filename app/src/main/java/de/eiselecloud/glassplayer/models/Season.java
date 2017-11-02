package de.eiselecloud.glassplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 30.10.17.
 */

public class Season{

    private List<Episode> episodes = new ArrayList<>();

    @SerializedName("season")
    @Expose
    private Integer season;

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public List<Episode> getEpisodes(){
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }
}