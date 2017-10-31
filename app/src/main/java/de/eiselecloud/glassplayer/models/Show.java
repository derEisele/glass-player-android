package de.eiselecloud.glassplayer.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by alexander on 30.10.17.
 */

public class Show {

    public Show(){

    }

    public Show(String title){
        this.title = title;
        this.poster = "";
        this.lang = "";
        this.year = 0;
        this.id = 0;
    }

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("lang")
    @Expose
    private String lang;

    @SerializedName("banner")
    @Expose
    private String banner;

    @SerializedName("poster")
    @Expose
    private String poster;

    @SerializedName("descr")
    @Expose
    private String descr;

    @SerializedName("year")
    @Expose
    private Integer year;

    @SerializedName("imdb_id")
    @Expose
    private String imdb_id;



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

    public String getLang() {
        return lang;
    }

    private void setLang(String lang) {
        this.lang = lang;
    }

    public String getBanner() {
        return banner;
    }

    private void setBanner(String banner) {
        this.banner = banner;
    }

    public String getPoster() {
        return poster;
    }

    private void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescr() {
        return descr;
    }

    private void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getYear() {
        return year;
    }

    private void setYear(Integer year) {
        this.year = year;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    private void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
