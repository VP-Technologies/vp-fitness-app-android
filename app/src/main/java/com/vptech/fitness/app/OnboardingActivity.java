package com.vptech.fitness.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.avontell.pagerindicatorbinder.IndicatorBinder;
import com.vptech.fitness.app.onboarding.AccountCreationAsyncTask;
import com.vptech.fitness.app.onboarding.OnboardingPagerAdapter;
import com.vptech.fitness.app.utils.ViewHelper;
import com.vptech.fitness.app.views.NonSwipeableViewPager;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * The onboarding activity for the fitness application
 * @author Tej Patel
 * @author Neil Patel
 * @author Aaron Vontell
 */
public class OnboardingActivity extends AppCompatActivity {

    private OnboardingPagerAdapter onboardingAdapter;
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_fragment);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        onboardingAdapter = new OnboardingPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter and correct configs
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(onboardingAdapter);
        mViewPager.setOffscreenPageLimit(10);

        // Set up indicator dots
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

    /**
     * Slides the ViewPager to the next page, if ready
     * @param view the Button making this call
     */
    public void nextPage(View view) {

        // Gauge what the current page and next page is
        int currentPage = mViewPager.getCurrentItem();
        int totalPages = mViewPager.getAdapter().getCount();
        int nextPage = currentPage + 1;
        if (nextPage >= totalPages) {
            finishOnboarding();
        }

        // Check to see that the information has been filled out correctly
        // If it is ready, move on. Otherwise, show an error dialog
        String ready = onboardingAdapter.getFragments().get(currentPage).ready();
        if(ready != null) {
            ViewHelper.createDialog(
                    this, getString(R.string.onboard_error_during_title),
                    ready, getString(R.string.onboard_error_pos), null, null, null).build().show();
        } else {
            mViewPager.setCurrentItem(nextPage, true);
        }

    }

    /**
     * Slides the ViewPager to the previous page, if available
     * @param view the Button making this call
     */
    public void previousPage(View view) {

        // Gauge what the current page and previous page is
        int currentPage = mViewPager.getCurrentItem();
        int previousPage = currentPage - 1;
        if (previousPage < 0) {
            return;
        }

        mViewPager.setCurrentItem(previousPage, true);
    }

    /**
     * Method to call when finished with the onboarding process.
     * Converts all onboarding data into JSONObject and sends it via HTTP calls
     * to the database
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

        // Now we want to post that information. Start our handy AsyncTask!
        new AccountCreationAsyncTask(this).execute(accountInformation, userInformation);

    }
}
