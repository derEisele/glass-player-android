package de.eiselecloud.glassplayer.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
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

public class SettingsFragment extends Fragment {
    View rootView;
    Button saveBtn;
    EditText serverInp;
    Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_view, container, false);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        saveBtn = (Button) rootView.findViewById(R.id.saveBtn);
        serverInp = (EditText) rootView.findViewById(R.id.serverInp);

        if (toolbar == null){ Log.e("GLASS", "Toolbar is null");}

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serverURL = serverInp.getText().toString();
                Log.i("GLASS", "Set sever URl to " + serverURL);

                SharedPreferences settings = rootView.getContext().getSharedPreferences(getResources().getString(R.string.prefKey), 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("baseUrl", serverURL);

                // Commit the edits!
                editor.commit();

            }
        });

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
