package de.eiselecloud.glassplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.eiselecloud.glassplayer.fragments.ShowTabOverview;

/**
 * Created by alexander on 28.10.17.
 */

public class MyShowPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public MyShowPagerAdapter(FragmentManager fm) { super(fm);}

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mFragmentTitleList.get(position);
    }

    public void addItem(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public ShowTabOverview getOverview(){
        return (ShowTabOverview) mFragmentList.get(0);
    }
}
