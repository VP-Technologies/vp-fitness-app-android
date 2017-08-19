package com.example.neil.vp_fitness_frontend.onboarding;

/**
 * A FragmentPager
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.neil.vp_fitness_frontend.OnboardingFragment;
import com.example.neil.vp_fitness_frontend.Tab1;
import com.example.neil.vp_fitness_frontend.Tab10;
import com.example.neil.vp_fitness_frontend.Tab2;
import com.example.neil.vp_fitness_frontend.Tab3;
import com.example.neil.vp_fitness_frontend.Tab4;
import com.example.neil.vp_fitness_frontend.Tab5;
import com.example.neil.vp_fitness_frontend.Tab6;
import com.example.neil.vp_fitness_frontend.Tab7;
import com.example.neil.vp_fitness_frontend.Tab8;
import com.example.neil.vp_fitness_frontend.Tab9;

import java.util.HashMap;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class OnboardingPagerAdapter extends FragmentPagerAdapter {

    /**
     * Mapping for all onboarding screens
     */
    private HashMap<Integer, OnboardingFragment> fragments;

    public OnboardingPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new HashMap<>();
    }

    public HashMap<Integer, OnboardingFragment> getFragments() {
        return fragments;
    }

    @Override
    public Fragment getItem(int position) { // getItem is called to instantiate the fragment for the given page.

        // Switch statement for tabs in onboarding fragment
        switch (position) {
            case 0:
                Tab1 tab1 = new Tab1();
                fragments.put(0, tab1);
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                fragments.put(1, tab2);
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                fragments.put(2, tab3);
                return tab3;
            case 3:
                Tab4 tab4 = new Tab4();
                fragments.put(3, tab4);
                return tab4;
            case 4:
                Tab5 tab5 = new Tab5();
                fragments.put(4, tab5);
                return tab5;
            case 5:
                Tab6 tab6 = new Tab6();
                fragments.put(5, tab6);
                return tab6;
            case 6:
                Tab7 tab7 = new Tab7();
                fragments.put(6, tab7);
                return tab7;
            case 7:
                Tab8 tab8 = new Tab8();
                fragments.put(7, tab8);
                return tab8;
            case 8:
                Tab9 tab9 = new Tab9();
                fragments.put(8, tab9);
                return tab9;
            case 9:
                Tab10 tab10 = new Tab10();
                fragments.put(9, tab10);
                return tab10;
        }
        return null; // returns null if there are no tabs
    }

    @Override
    public int getCount() {
        // Show 10 total pages.
        return 10;
    }

}
