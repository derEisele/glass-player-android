package de.eiselecloud.glassplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class ShowListActivity extends AppCompatActivity implements ShowListItem.ShowCallBack{
    Toolbar toolbar;
    PlaceHolderView placeHolderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        placeHolderView = (PlaceHolderView) findViewById(R.id.phl_shows);
        setSupportActionBar(toolbar);

        setupShowList();


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setupShowList(){
        List<ShowListItem> showItems = new ArrayList<>();
        showItems.add(new ShowListItem(this, 1, "Show 1"));
        showItems.add(new ShowListItem(this, 2, "Show 2"));
        showItems.add(new ShowListItem(this, 3, "Show 3"));
        showItems.add(new ShowListItem(this, 4, "Show 4"));
        showItems.add(new ShowListItem(this, 5, "Show 5"));

        for(ShowListItem item: showItems){
            item.setShowCallBack(this);
            placeHolderView.addView(item);
            Log.i("GLASS", "Added item");
        }
    }

    @Override
    public void onClick(int showID) {
        Log.i("GLASS", "Select show with ID " + showID);
    }
}
