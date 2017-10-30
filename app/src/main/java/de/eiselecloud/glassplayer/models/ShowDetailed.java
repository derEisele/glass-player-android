package de.eiselecloud.glassplayer.models;

import java.util.ArrayList;

/**
 * Created by alexander on 30.10.17.
 */

public class ShowDetailed extends Show{

    private ArrayList<Season> seasons = new ArrayList<>();

    public ArrayList<Season> getSeasons(){
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }


}
