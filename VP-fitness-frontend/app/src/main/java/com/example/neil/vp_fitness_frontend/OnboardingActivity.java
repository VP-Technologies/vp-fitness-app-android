package com.example.neil.vp_fitness_frontend;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.avontell.pagerindicatorbinder.IndicatorBinder;
import com.example.neil.vp_fitness_frontend.utils.API;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class OnboardingActivity extends AppCompatActivity {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * Context for this activity
     */
    private Context context;

    /**
     * Mapping for all onboarding screens
     */
    HashMap<String, OnboardingFragment> fragments = new HashMap<>();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ImageButton rightArrow;
    protected int pageId;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void nextPage(View view) {
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();

        int nextPage = currentPage + 1;
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
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Onboarding Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
     * Converts all onboarding data into JSONObject and sends it via HTTP call to database
     */
    public void finish(View view) {

        // JSON Objects to be POSTed to the server
        JSONObject accountInformation = new JSONObject();
        JSONObject userInformation = new JSONObject();

        for (OnboardingFragment fragment : fragments.values()) {

            // For each fragment, grab the data and put it into the appropriate JSON
            HashMap<String, String> data = fragment.getData();
            for (String key : data.keySet()) {
                try {
                    String value = data.get(key);
                    switch (key) {
                        case "username":
                        case "email":
                        case "name":
                        case "password":
                            accountInformation.put(key, value);
                            break;
                        case "age":
                        case "height":
                        case "weight":
                        case "goal_weight":
                        case "goal":
                        case "difficulty":
                            userInformation.put(key, Integer.parseInt(value));
                            break;
                        case "equipment":
                            userInformation.put(key, value);
                        default:
                            throw new RuntimeException("An invalid key was encountered during the onboarding process.");
                    }

                } catch (JSONException e) {
                    throw new RuntimeException("An error occurred during the onboarding process:\n\t" + e.toString());
                }
            }

            // Now we want to post that information. Start our handy AsyncTask!
            new OnboardAsyncTask().execute(accountInformation, userInformation);

        }
    }

    private class OnboardAsyncTask extends AsyncTask<JSONObject, Void, Void> {

        @Override
        protected Void doInBackground(JSONObject... objs) {

            try {
                // First create the account
                API.postAccountCreation(objs[0]);

                // Now authenticated so we can create the user info. The resulting
                // access token should be saved! postAuthentication will automatically
                // save the access token for us; ain't that nifty? And probs dangerous...
                API.postAuthentication(objs[0].getString("username"), objs[0].getString("password"));

                // Then login automatically, to create user information
                API.postUserInfo(objs[1]);
            }
            catch (Exception e) {
                throw new RuntimeException("There was an error during account creation:\n\t" + e.toString());
            }

            return null;

        }
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
                    fragments.put("TAB4", tab4);
                    return tab4;
                case 4:
                    Tab5 tab5 = new Tab5();
                    fragments.put("TAB5", tab5);
                    return tab5;
                case 5:
                    Tab6 tab6 = new Tab6();
                    fragments.put("TAB6", tab6);
                    return tab6;
                case 6:
                    Tab7 tab7 = new Tab7();
                    return tab7;
                case 7:
                    Tab8 tab8 = new Tab8();
                    fragments.put("TAB8", tab8);
                    return tab8;
                case 8:
                    Tab9 tab9 = new Tab9();
                    return tab9;
                case 9:
                    Tab10 tab10 = new Tab10();
                    fragments.put("TAB10", tab10);
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
