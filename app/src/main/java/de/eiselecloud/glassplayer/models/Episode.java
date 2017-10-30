package de.eiselecloud.glassplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander on 30.10.17.
 */

public class Episode{
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("descr")
    @Expose
    private String descr;

    @SerializedName("season")
    @Expose
    private Integer season;

    @SerializedName("episode")
    @Expose
    private Integer episode;



    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    private void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getSeason() {
        return season;
    }

    private void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getEpisode() {
        return episode;
    }

    private void setEpisode(Integer episode) {
        this.episode = episode;
    }
}
