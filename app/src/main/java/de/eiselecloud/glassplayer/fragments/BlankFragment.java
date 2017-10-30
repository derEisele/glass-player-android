package de.eiselecloud.glassplayer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.eiselecloud.glassplayer.R;

/**
 * Created by alexander on 29.10.17.
 */

//A hack to avoid crash an start :P
public class BlankFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.blank_view, container, false);
    }
}
