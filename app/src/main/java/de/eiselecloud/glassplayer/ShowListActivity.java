package de.eiselecloud.glassplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

import de.eiselecloud.glassplayer.adapters.ShowListAdapter;
import de.eiselecloud.glassplayer.models.Show;

public class ShowListActivity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView showsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

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
        showsView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        List<Show> showItems = new ArrayList<>();
        showItems.add(new Show("Test 1"));
        showItems.add(new Show("Test 2"));

        showsView.setAdapter(new ShowListAdapter(showItems));




    }
}
