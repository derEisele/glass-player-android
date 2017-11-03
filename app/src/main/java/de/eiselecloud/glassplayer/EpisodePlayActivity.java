package de.eiselecloud.glassplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import de.eiselecloud.glassplayer.models.Episode;
import de.eiselecloud.glassplayer.models.EpisodeDetailed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodePlayActivity extends AppCompatActivity {
    MyVideoPlayer videoPlayer;
    int episodeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode_play);

        Bundle b = getIntent().getExtras();
        episodeID = 1;
        if(b != null)
            episodeID = b.getInt("episodeID");

        SimpleExoPlayerView playerView = (SimpleExoPlayerView) findViewById(R.id.video_player);

        videoPlayer = new MyVideoPlayer(this);
        videoPlayer.initPlayer(playerView);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        loadInfo();

    }

    @Override
    protected void onStop() {
        super.onStop();
        videoPlayer.stop();

    }

    private void loadInfo(){
        if(isNetworkConnected()) {
            final ProgressDialog dialog;

            dialog = new ProgressDialog(EpisodePlayActivity.this);
            dialog.setTitle(getString(R.string.getting_episode_info_title));
            dialog.setMessage(getString(R.string.getting_episode_info_msg));
            dialog.show();

            GlassService glass = RetroClient.getGlassService(getBaseURL());
            Call<EpisodeDetailed> call = glass.getEpisode(episodeID);
            call.enqueue(new Callback<EpisodeDetailed>() {
                @Override
                public void onResponse(Call<EpisodeDetailed> call, Response<EpisodeDetailed> response) {
                    if(response.isSuccessful()) {
                        String urlString = response.body().getUrls().getHls();
                        Uri url = Uri.parse(urlString.replace("master", "index_2000"));
                        videoPlayer.playHLS(url);
                    }

                    dialog.dismiss();
                }

                @Override
                public void onFailure(Call<EpisodeDetailed> call, Throwable t) {
                    dialog.dismiss();
                }
            });

        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    private String getBaseURL(){
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.prefKey), 0);
        return  settings.getString("baseUrl", "");
    }
}
