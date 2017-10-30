package de.eiselecloud.glassplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.eiselecloud.glassplayer.MainActivity;
import de.eiselecloud.glassplayer.R;

/**
 * Created by alexander on 29.10.17.
 */

public class MovieFragment extends Fragment {
    View rootView;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.movie_view, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        if (toolbar == null){ Log.e("GLASS", "Toolbar is null");}

        return rootView;
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
