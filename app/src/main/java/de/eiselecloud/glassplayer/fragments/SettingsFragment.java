package de.eiselecloud.glassplayer.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import de.eiselecloud.glassplayer.MainActivity;
import de.eiselecloud.glassplayer.R;

/**
 * Created by alexander on 29.10.17.
 */

public class SettingsFragment extends Fragment implements View.OnClickListener {
    View rootView;
    EditText serverInp;
    EditText folderInp;
    Toolbar toolbar;
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_view, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        Button saveBtn = (Button) rootView.findViewById(R.id.saveBtn);
        serverInp = (EditText) rootView.findViewById(R.id.serverInp);
        folderInp = (EditText) rootView.findViewById(R.id.folderInp);

        settings = rootView.getContext().getSharedPreferences(getResources().getString(R.string.prefKey), 0);
        editor = settings.edit();

        serverInp.setText(settings.getString("baseUrl", ""));
        folderInp.setText(settings.getString("mediaFolder", ""));


        if (toolbar == null){ Log.e("GLASS", "Toolbar is null");}

        saveBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {

        SharedPreferences settings = rootView.getContext().getSharedPreferences(getResources().getString(R.string.prefKey), 0);
        SharedPreferences.Editor editor = settings.edit();

        switch (v.getId()) {
            case R.id.saveBtn:
                String serverURL = "" + serverInp.getText().toString();
                String folder = "" + folderInp.getText().toString();


                if (serverURL != "") {

                    Log.i("GLASS", "Set sever URl to " + serverURL);

                    editor.putString("baseUrl", serverURL);

                    editor.commit();
                }

                if (folder != ""){
                    Log.i("GLASS", "Set media folder to " + folder);

                    editor.putString("mediaFolder", folder);
                    editor.commit();
                }

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
