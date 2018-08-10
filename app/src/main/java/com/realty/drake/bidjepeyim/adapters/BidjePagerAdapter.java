package com.realty.drake.bidjepeyim.adapters;

/*
 * Created by drake on 8/9/18
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.realty.drake.bidjepeyim.fragments.BudgetFragment;
import com.realty.drake.bidjepeyim.fragments.NewsFragment;
import com.realty.drake.bidjepeyim.fragments.RegulatoryFrameworkFragment;
import com.realty.drake.bidjepeyim.fragments.StatisticsFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class BidjePagerAdapter extends FragmentPagerAdapter {


    public BidjePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("DEBUGG","getItem called in FragmentPagerAdapter" );
        //Returning the current tab
        switch (position) {
            case 0:
                return new NewsFragment();
            case 1:
                return new BudgetFragment();
            case 2:
                return new StatisticsFragment();
            case 3:
                return new RegulatoryFrameworkFragment();
            default:
                return  null;
        }
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position){
        Log.d("Debugg", "getPageTitle: Executed");
        switch (position) {
            case 0:
                return "Actualites";
            case 1:
                return "Budget";
            case 2:
                return "Statistiques";
            case 3:
                return "Cadre Legal";
        }
        return null;
    }
}
