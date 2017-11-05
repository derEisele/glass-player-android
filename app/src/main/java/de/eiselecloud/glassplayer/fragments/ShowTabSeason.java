package de.eiselecloud.glassplayer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import de.eiselecloud.glassplayer.EpisodePlayActivity;
import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.adapters.EpisodeListAdapter;
import de.eiselecloud.glassplayer.models.Episode;

/**
 * Created by alexander on 28.10.17.
 */

public class ShowTabSeason extends Fragment implements EpisodeListAdapter.OnItemClickListener{

    private View rootView;
    private RecyclerView recyclerView;
    public EpisodeListAdapter adapter;
    public List<Episode> myEpisodes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.show_tab_season, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.episodesRv);
        if (recyclerView == null){
            Log.e("GLASS", "Episodes list == null");
        }
        adapter = new EpisodeListAdapter(getActivity());
        setupRecyclerView();
        if(myEpisodes != null){
            adapter.setEpisodes(myEpisodes);
        }

        return rootView;
    }

    private void setupRecyclerView(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        adapter.setClickListener(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

    }

    public void loadEpisodes(List<Episode> episodes){
        myEpisodes = episodes;
        if(adapter!=null){
            adapter.setEpisodes(myEpisodes);
        }else {
            Log.e("GLASS", "EpisodeListAdapter == null");
        }
    }

    @Override
    public void onClick(View view, int position) {
        Intent intent = new Intent(getActivity(), EpisodePlayActivity.class);
        intent.putExtra("episodeID", adapter.getEpisodeByPosition(position).getId());
        startActivity(intent);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
