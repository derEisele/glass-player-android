package de.eiselecloud.glassplayer;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DrawerMenuItem.DrawerCallBack{

    private PlaceHolderView mDrawerView;
    private DrawerLayout mDrawer;
    //private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);
        //mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setupDrawer();

        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragment, homeFragment);
        ft.commit();
    }

    private void setupDrawer(){

        List<DrawerMenuItem> menuItems = new ArrayList<DrawerMenuItem>();

        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_HOME));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MUSIC));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SHOW));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MOVIE));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_LIVETV));
        menuItems.add(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS));

        mDrawerView.addView(new DrawerHeader());

        for(DrawerMenuItem item: menuItems){
            item.setDrawerCallBack(this);
            mDrawerView.addView(item);
        }
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
    public void onHomeMenuSelected() {
        mDrawer.closeDrawers();
    }

    @Override
    public void onMusicMenuSelected() {
        mDrawer.closeDrawers();
    }

    @Override
    public void onShowMenuSelected() {
        ShowFragment showFragment = new ShowFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, showFragment);
        ft.commit();
        mDrawer.closeDrawers();
    }

    @Override
    public void onMovieMenuSelected() {
        MovieFragment movieFragment = new MovieFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, movieFragment);
        ft.commit();
        mDrawer.closeDrawers();
    }

    @Override
    public void onLiveTVMenuSelected() {
        mDrawer.closeDrawers();
    }

    @Override
    public void onSettingsMenuSelected() {
        mDrawer.closeDrawers();
    }
}