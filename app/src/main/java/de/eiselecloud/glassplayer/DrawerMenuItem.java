package de.eiselecloud.glassplayer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import static android.R.attr.fragment;

/**
 * Created by alexander on 29.10.17.
 */

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_HOME     = 1;
    public static final int DRAWER_MENU_ITEM_MUSIC    = 2;
    public static final int DRAWER_MENU_ITEM_SHOW     = 3;
    public static final int DRAWER_MENU_ITEM_MOVIE    = 4;
    public static final int DRAWER_MENU_ITEM_LIVETV   = 5;
    public static final int DRAWER_MENU_ITEM_SETTINGS = 6;


    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                itemNameTxt.setText(R.string.home);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_18dp));
                break;
            case DRAWER_MENU_ITEM_MUSIC:
                itemNameTxt.setText(R.string.music);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_music_note_black_18dp));
                break;
            case DRAWER_MENU_ITEM_SHOW:
                itemNameTxt.setText(R.string.show);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_tv_black_18dp));
                break;
            case DRAWER_MENU_ITEM_MOVIE:
                itemNameTxt.setText(R.string.movie);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_movie_black_18dp));
                break;
            case DRAWER_MENU_ITEM_LIVETV:
                itemNameTxt.setText(R.string.livetv);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_live_tv_black_18dp));
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                itemNameTxt.setText(R.string.settings);
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_settings_black_18dp));
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_HOME:
                if(mCallBack != null)mCallBack.onHomeMenuSelected();
                break;
            case DRAWER_MENU_ITEM_MUSIC:
                Toast.makeText(mContext, "Music: TODO", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onMusicMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SHOW:
                if(mCallBack != null)mCallBack.onShowMenuSelected();
                break;
            case DRAWER_MENU_ITEM_MOVIE:
                if(mCallBack != null)mCallBack.onMovieMenuSelected();
                break;
            case DRAWER_MENU_ITEM_LIVETV:
                Toast.makeText(mContext, "Live TV: TODO", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onLiveTVMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                Toast.makeText(mContext, "Settings: TODO", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onSettingsMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onHomeMenuSelected();
        void onMusicMenuSelected();
        void onShowMenuSelected();
        void onMovieMenuSelected();
        void onLiveTVMenuSelected();
        void onSettingsMenuSelected();
    }
}
