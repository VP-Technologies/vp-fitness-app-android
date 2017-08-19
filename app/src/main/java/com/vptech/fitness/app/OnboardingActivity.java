package com.vptech.fitness.app;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.avontell.pagerindicatorbinder.IndicatorBinder;
import com.vptech.fitness.app.onboarding.AccountCreationAsyncTask;
import com.vptech.fitness.app.onboarding.OnboardingPagerAdapter;
import com.vptech.fitness.app.utils.ViewHelper;
import com.vptech.fitness.app.views.NonSwipeableViewPager;

import org.json.JSONObject;
import java.util.HashMap;

public class OnboardingActivity extends AppCompatActivity {

    private OnboardingPagerAdapter onboardingAdapter;
    private NonSwipeableViewPager mViewPager;
    private ImageButton rightArrow;
    protected int pageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_fragment);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        onboardingAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(onboardingAdapter);
        mViewPager.setOffscreenPageLimit(10);
        LinearLayout indicatorContainer = findViewById(R.id.indicator_cont);
        int selectedImage = R.drawable.indicator_selected;
        int unselectedImage = R.drawable.indicator_unselected;
        final IndicatorBinder sample = new IndicatorBinder().bind(this,
                mViewPager,
                indicatorContainer,
                selectedImage,
                unselectedImage);
        sample.setProgressStyle(false);

    }

    public void nextPage(View view) {
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();

        int nextPage = currentPage + 1;
        if (nextPage >= totalPages) {
            finishOnboarding();
        }

        // Check to see that the information has been filled
        String ready = onboardingAdapter.getFragments().get(currentPage).ready();
        if(ready != null) {
            ViewHelper.createDialog(
                    this, getString(R.string.onboard_error_during_title),
                    ready, getString(R.string.onboard_error_pos), null, null, null).build().show();
        } else {
            mViewPager.setCurrentItem(nextPage, true);
        }

    }

    public void previousPage(View view) {
        int currentPage = mViewPager.getCurrentItem();

        int previousPage = currentPage - 1;
        if (previousPage < 0) {
            return;
        }

        mViewPager.setCurrentItem(previousPage, true);
    }

    /**
     * Method to call when finished with the onboarding process.
     * Converts all onboarding data into JSONObject and sends it via HTTP call to database
     */
    public void finishOnboarding() {

        // JSON Objects to be POSTed to the server
        JSONObject accountInformation = new JSONObject();
        JSONObject userInformation = new JSONObject();

        for (OnboardingFragment fragment : onboardingAdapter.getFragments().values()) {

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
                            break;
                        default:
                            throw new Exception("Found bad key: " + key);
                    }

                } catch (Exception e) {
                    // Show an error dialog if we don't know what happened
                    ViewHelper.createDialog(
                            this, getString(R.string.onboard_error_title),
                            getString(R.string.onboard_error_content) + " " + e.toString(),
                            getString(R.string.onboard_error_pos), null, null, null).build().show();
                }
            }
        }

        Log.e("ACCOUNT INFO", accountInformation.toString());
        Log.e("USER INFO", userInformation.toString());

        // Now we want to post that information. Start our handy AsyncTask!
        new AccountCreationAsyncTask(this).execute(accountInformation, userInformation);

    }
}
