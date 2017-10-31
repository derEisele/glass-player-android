package de.eiselecloud.glassplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import de.eiselecloud.glassplayer.adapters.ShowListAdapter;
import de.eiselecloud.glassplayer.models.Show;
import de.eiselecloud.glassplayer.models.ShowList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowListActivity extends AppCompatActivity implements ShowListAdapter.OnItemClickListener{
    Toolbar toolbar;
    RecyclerView showsView;
    ShowListAdapter adapter;
    List<Show> showList;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        rootView = findViewById(R.id.showsLL);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        showsView = (RecyclerView) findViewById(R.id.showsView);
        setSupportActionBar(toolbar);

        setupShowList();


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupShowList(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        showsView.setLayoutManager(mLayoutManager);


        loadShows();
        if (adapter != null) {
            adapter.setClickListener(this);
            showsView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        }
    }

    @Override
    public void onClick(View view, int position) {
        String title = adapter.getShowByPosition(position).getTitle();
        Toast.makeText(this, title + " selected!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    private void loadShows(){
        if(isNetworkConnected()){
            final ProgressDialog dialog;

            dialog = new ProgressDialog(ShowListActivity.this);
            dialog.setTitle(getString(R.string.getting_shows_title));
            dialog.setMessage(getString(R.string.getting_shows_msg));
            dialog.show();

            GlassService glass = RetroClient.getGlassService(getBaseURL());
            Call<ShowList> call = glass.getShows();

            call.enqueue(new Callback<ShowList>() {
                @Override
                public void onResponse(Call<ShowList> call, Response<ShowList> response) {
                    dialog.dismiss();

                    if(response.isSuccessful()){
                        showList = response.body().getShows();
                        adapter = new ShowListAdapter(ShowListActivity.this, showList);
                        showsView.setAdapter(adapter);
                    } else {
                        Snackbar.make(rootView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ShowList> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                }
            });

        } else {
            Snackbar.make(rootView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
        }
    }

    private String getBaseURL(){
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.prefKey), 0);
        return  settings.getString("baseUrl", "");
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
