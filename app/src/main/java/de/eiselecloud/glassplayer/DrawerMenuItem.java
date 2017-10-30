package de.eiselecloud.glassplayer;


import android.content.Context;
import android.view.View;

/**
 * Created by alexander on 29.10.17.
 */

public class DrawerMenuItem {
    private String itemName;
    private int itemIconResource;

    public final static int DRAWER_MENU_ITEM_HOME = 1;
    public final static int DRAWER_MENU_ITEM_MUSIC = 2;
    public final static int DRAWER_MENU_ITEM_SHOW = 3;
    public final static int DRAWER_MENU_ITEM_MOVIE = 4;
    public final static int DRAWER_MENU_ITEM_LIVETV = 5;
    public final static int DRAWER_MENU_ITEM_SETTINGS = 6;

    public DrawerMenuItem(String itemName, int iconResource){
        this.itemName = itemName;
        this.itemIconResource = iconResource;
    }

    public DrawerMenuItem(Context context, int type){
        switch (type){
            case DRAWER_MENU_ITEM_HOME:
                itemName = context.getResources().getString(R.string.home);
                itemIconResource = R.drawable.ic_home_black_18dp;
                break;
            case DRAWER_MENU_ITEM_MUSIC:
                itemName = context.getResources().getString(R.string.music);
                itemIconResource = R.drawable.ic_music_note_black_18dp;
                break;
            case DRAWER_MENU_ITEM_SHOW:
                itemName = context.getResources().getString(R.string.show);
                itemIconResource = R.drawable.ic_tv_black_18dp;
                break;
            case DRAWER_MENU_ITEM_MOVIE:
                itemName = context.getResources().getString(R.string.movie);
                itemIconResource = R.drawable.ic_movie_black_18dp;
                break;
            case DRAWER_MENU_ITEM_LIVETV:
                itemName = context.getResources().getString(R.string.livetv);
                itemIconResource = R.drawable.ic_live_tv_black_18dp;
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                itemName = context.getResources().getString(R.string.settings);
                itemIconResource = R.drawable.ic_settings_black_18dp;
                break;
        }
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemIconResource() {
        return itemIconResource;
    }
}
