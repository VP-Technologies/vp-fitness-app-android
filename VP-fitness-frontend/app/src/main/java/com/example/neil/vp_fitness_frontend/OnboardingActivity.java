package com.example.neil.vp_fitness_frontend;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.avontell.fontutil.FontUtil;
import com.avontell.pagerindicatorbinder.IndicatorBinder;
import com.matthewtamlin.sliding_intro_screen_library.indicators.DotIndicator;

import java.util.HashMap;

public class OnboardingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * Context for this activity
     */
    private Context context;

    /**
     * Mapping for all onboarding screens
     */
    HashMap<String, Fragment> fragments = new HashMap<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageButton rightArrow;
    protected int pageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_fragment);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(10);
        LinearLayout indicatorContainer = (LinearLayout) findViewById(R.id.indicator_cont);
        int selectedImage = R.drawable.indicator_selected;
        int unselectedImage = R.drawable.indicator_unselected;
        final IndicatorBinder sample = new IndicatorBinder().bind(this,
                mViewPager,
                indicatorContainer,
                selectedImage,
                unselectedImage);
        sample.setProgressStyle(false);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void nextPage(View view) {
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();

        int nextPage = currentPage+1;
        if (nextPage >= totalPages) {
            return;
        }

        mViewPager.setCurrentItem(nextPage, true);
    }

    public void previousPage(View view) {
            int currentPage = mViewPager.getCurrentItem();
            int totalPages = mViewPager.getAdapter().getCount();

            int previousPage = currentPage - 1;
            if (previousPage < 0) {
                return;
            }

            mViewPager.setCurrentItem(previousPage, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_onboarding, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        pageId = id;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tab1, container, false);
            return rootView;
        }
    }

    /**
     * Method to call when finished with the onboarding process.
     */
    public void finish(View view) {

        OnboardingFragment tab = (OnboardingFragment) fragments.get("TAB3");
        Log.e("RESULTS", tab.getData().toString());

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) { // getItem is called to instantiate the fragment for the given page.

            // Switch statement for tabs in onboarding fragment
            switch (position) {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;
                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
                case 2:
                    Tab3 tab3 = new Tab3();
                    fragments.put("TAB3", tab3);
                    return tab3;
                case 3:
                    Tab4 tab4 = new Tab4();
                    return tab4;
                case 4:
                    Tab5 tab5 = new Tab5();
                    return tab5;
                case 5:
                    Tab6 tab6 = new Tab6();
                    return tab6;
                case 6:
                    Tab7 tab7 = new Tab7();
                    return tab7;
                case 7:
                    Tab8 tab8 = new Tab8();
                    return tab8;
                case 8:
                    Tab9 tab9 = new Tab9();
                    return tab9;
                case 9:
                    Tab10 tab10 = new Tab10();
                    return tab10;
            }
            return null; // returns null if there are no tabs
        }

        @Override
        public int getCount() {
            // Show 10 total pages.
            return 10;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }


    }
}
