package de.eiselecloud.glassplayer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.eiselecloud.glassplayer.MainActivity;
import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.ShowListActivity;

/**
 * Created by alexander on 29.10.17.
 */

public class ShowFragment extends Fragment implements View.OnClickListener{
    View rootView;
    Button btnAllShows;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView = inflater.inflate(R.layout.show_view, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        if (toolbar == null){ Log.e("GLASS", "Toolbar is null");}

        btnAllShows = (Button) rootView.findViewById(R.id.btnAllShows);
        btnAllShows.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAllShows:
                Intent intent = new Intent(getActivity(), ShowListActivity.class);
                startActivity(intent);
                return;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setToolbar(toolbar);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity)getActivity()).setToolbar(null);
        super.onDestroyView();
    }
}
