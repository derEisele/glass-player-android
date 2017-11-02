package de.eiselecloud.glassplayer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import de.eiselecloud.glassplayer.GlassService;
import de.eiselecloud.glassplayer.R;
import de.eiselecloud.glassplayer.RetroClient;
import de.eiselecloud.glassplayer.adapters.MyShowPagerAdapter;
import de.eiselecloud.glassplayer.fragments.ShowTabOverview;
import de.eiselecloud.glassplayer.fragments.ShowTabSeason;
import de.eiselecloud.glassplayer.models.Season;
import de.eiselecloud.glassplayer.models.ShowDetailed;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private int showID;
    private String title;
    private CollapsingToolbarLayout toolbarLayout;
    private View parentView;
    private ImageView toolbarImageView;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private MyShowPagerAdapter adapter;
    private ViewPager viewPager;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getIntent().getExtras();
        showID = 1;
        if(b != null)
            showID = b.getInt("showID");

        setContentView(R.layout.activity_show);
        parentView = findViewById(R.id.show_parent);

        toolbarImageView = (ImageView) findViewById(R.id.showTbImg);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.showTbL);
        toolbar = (Toolbar) findViewById(R.id.show_toolbar);


        viewPager = (ViewPager) findViewById(R.id.showVp);

        if(viewPager != null){
            setupViewPager(viewPager);
        }

        tabLayout = (TabLayout) findViewById(R.id.showTbs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setOnTabSelectedListener(this);

        baseUrl = getBaseURL();

        setSupportActionBar(toolbar);
        loadInfo();
    }


    private void loadInfo(){
        if(isNetworkConnected()){
            final ProgressDialog dialog;

            dialog = new ProgressDialog(ShowActivity.this);
            dialog.setTitle(getString(R.string.getting_show_info_title));
            dialog.setMessage(getString(R.string.getting_show_info_msg));
            dialog.show();

            GlassService glass = RetroClient.getGlassService(baseUrl);
            Call<ShowDetailed> call = glass.getShow(showID);

            call.enqueue(new Callback<ShowDetailed>() {
                @Override
                public void onResponse(Call<ShowDetailed> call, Response<ShowDetailed> response) {
                    dialog.dismiss();

                    if(response.isSuccessful()){
                        title = response.body().getTitle();
                        String banner = response.body().getBanner();
                        banner = banner.replace("http://", "https://www.");
                        //Snackbar.make(parentView, banner, Snackbar.LENGTH_LONG).show();
                        toolbarLayout.setTitle(title);
                        setToolbarBackground(response.body().getPoster());

                        List<Season> seasons = response.body().getSeasons();
                        adapter.getOverview().setDescription(response.body().getDescr());

                        for (Season s: seasons) {
                            int seasonNumber = s.getSeason();


                            if (s.getEpisodes() == null) {
                                Log.e("GLASS", "Episodes null!");
                            }else {
                                ShowTabSeason seasonTab = new ShowTabSeason();
                                adapter.addItem(seasonTab, "Season " + seasonNumber);
                                seasonTab.loadEpisodes(s.getEpisodes());

                            }
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        Snackbar.make(parentView, R.string.string_some_thing_wrong, Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ShowDetailed> call, Throwable t) {
                    dialog.dismiss();
                    t.printStackTrace();
                }
            });

        } else {
            Snackbar.make(parentView, R.string.string_internet_connection_not_available, Snackbar.LENGTH_LONG).show();
        }
    }

    private void setupViewPager(ViewPager viewPager){
        Log.i("GLASS", "Setup Viewpager");
        adapter = new MyShowPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new ShowTabOverview(), "Overview");
        viewPager.setAdapter(adapter);
    }

    private String getBaseURL(){
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.prefKey), 0);
        return  settings.getString("baseUrl", "");
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Palette.Swatch vibrantSwatch = Palette.from(bitmap).generate().getVibrantSwatch();
            //Palette.Swatch dominantSwatch = Palette.from(bitmap).generate().getDominantSwatch();

            Palette.Swatch mySwatch;

            if (vibrantSwatch != null){
                mySwatch = vibrantSwatch;
            } else {
                Snackbar.make(parentView, "Use dominant swatch", Snackbar.LENGTH_LONG).show();
                mySwatch = Palette.from(bitmap).generate().getDominantSwatch();
            }


            if (mySwatch == null) {
                Snackbar.make(parentView, "Unable to create swatch", Snackbar.LENGTH_LONG).show();
            }else{

                getWindow().setStatusBarColor(mySwatch.getRgb());
                //toolbarLayout.setBackgroundColor(vibrantSwatch.getRgb());
                toolbarLayout.setContentScrimColor(mySwatch.getRgb());
                tabLayout.setBackgroundColor(mySwatch.getRgb());


                float lumi = mySwatch.getHsl()[2];
                Log.i("GLASS", "lumi: " + lumi);
                int textColor;

                if (lumi > 0.5){
                    textColor = Color.parseColor("#000000");
                }else{
                    textColor = Color.parseColor("#FFFFFF");
                }

                toolbarLayout.setCollapsedTitleTextColor(textColor);
                tabLayout.setTabTextColors(textColor, textColor);
                tabLayout.setSelectedTabIndicatorColor(textColor);



            }
            toolbarImageView.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
            //toolbarLayout.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Snackbar.make(parentView, "Failed to load image", Snackbar.LENGTH_LONG).show();
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };


    private void setToolbarBackground(String url) {

        int x = toolbarImageView.getWidth();
        int y = toolbarImageView.getHeight();
        Picasso.with(getApplicationContext())
                .load(url).resize(x, y).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(target);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


}
