package de.eiselecloud.glassplayer.models;

import java.util.ArrayList;

/**
 * Created by alexander on 30.10.17.
 */

public class ShowList {
    private ArrayList<Show> shows = new ArrayList<>();

    public ArrayList<Show> getShows(){
        return shows;
    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
    }
}
