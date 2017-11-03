package de.eiselecloud.glassplayer.models;

/**
 * Created by alexander on 03.11.17.
 */

public class EpisodeDetailed extends Episode {
    private Urls urls;
    private int show_id;

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Urls getUrls() {
        return urls;
    }

    public int getShow_id() {
        return show_id;
    }

    public void setShow_id(int show_id) {
        this.show_id = show_id;
    }
}
