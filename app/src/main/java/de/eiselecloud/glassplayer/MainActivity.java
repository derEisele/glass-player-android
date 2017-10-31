package de.eiselecloud.glassplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


import java.util.ArrayList;
import java.util.List;

import de.eiselecloud.glassplayer.adapters.DrawerAdapter;
import de.eiselecloud.glassplayer.fragments.HomeFragment;
import de.eiselecloud.glassplayer.fragments.MovieFragment;
import de.eiselecloud.glassplayer.fragments.ShowFragment;


public class MainActivity extends AppCompatActivity implements DrawerAdapter.OnItemClickListener{

    private RecyclerView mDrawerView;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerView = (RecyclerView) findViewById(R.id.drawerView);

        setupDrawer();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragment, homeFragment);
        ft.commit();
    }

    private void setupDrawer(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mDrawerView.setLayoutManager(mLayoutManager);

        List<DrawerMenuItem> menuItems = new ArrayList<DrawerMenuItem>();

        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_HOME));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MUSIC));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SHOW));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MOVIE));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_LIVETV));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS));

        DrawerAdapter adapter = new DrawerAdapter(menuItems);
        mDrawerView.setAdapter(adapter);
        adapter.setClickListener(this);
        mDrawerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


    }

    public void setToolbar(Toolbar toolbar) {
        if(toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawer, toolbar, R.string.open_drawer, R.string.close_drawer);
            mDrawer.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            mDrawer.setDrawerListener(null);
        }
    }

    @Override
    public void onClick(View view, int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                //Header
                break;
            case 1:
                //Home
                fragment = new HomeFragment();
                break;
            case 2:
                //Music
                break;
            case 3:
                //Show
                fragment = new ShowFragment();
                break;
            case 4:
                fragment = new MovieFragment();
                //Movie
                break;
            case 5:
                //Live TV
                break;
            case 6:
                //Settings
                break;
            default:
                //Just in case
                fragment = new HomeFragment();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFragment, fragment).commit();
        }
        mDrawer.closeDrawers();
    }
}