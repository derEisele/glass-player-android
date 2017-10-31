package de.eiselecloud.glassplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.adapters.EpisodeListAdapter;
import de.eiselecloud.glassplayer.models.Episode;

/**
 * Created by alexander on 28.10.17.
 */

public class ShowTabSeason extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    List<Episode> episodes;
    EpisodeListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.show_tab_season, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.episodesRv);
        adapter = new EpisodeListAdapter(getActivity(), episodes);
        setupRecyclerView();
        return rootView;
    }

    private void setupRecyclerView(){
        recyclerView.setAdapter(adapter);
    }

    public void loadEpisodes(List<Episode> episodes){
        this.episodes = episodes;
    }
}
