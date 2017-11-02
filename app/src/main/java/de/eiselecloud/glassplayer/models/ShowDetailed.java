package de.eiselecloud.glassplayer.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexander on 30.10.17.
 */

public class ShowDetailed extends Show{

    private List<Season> seasons = new ArrayList<>();

    public List<Season> getSeasons(){
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }


}
