package com.example.team2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numOfTabs = numOfTabs;

    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new SportsTrainingFragment();
            case 1:
                return new HealthAndWellnessFragment();
            case 2:
                return new ChildrensSportsFragment();
            case 3:
                return new CalendarFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}