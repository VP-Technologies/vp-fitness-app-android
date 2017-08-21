package com.vptech.fitness.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vptech.fitness.app.utils.Constants;

/**
 * Activity that gets called upon starting the VP application. It is not really meant to have
 * any user interface; it decides whether or not if the user has 1) registered before on this
 * device, and 2) if the user has already logged in on this device.
 * @author Aaron Vontell
 */
public class StartActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = this.getSharedPreferences(Constants.SHARED_PREFS_KEY, MODE_PRIVATE);

        // If 'hasLoggedIn' is true, then proceed to the main app.
        // Otherwise, if the user has registered, move to the login page
        // Finally, if both are false, then let's move to registration
        if (hasLoggedIn()) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            finish();
            startActivity(mainActivityIntent);
        }
        else if (hasRegistered()) {
            Intent loginActivityIntent = new Intent(this, LoginActivity.class);
            finish();
            startActivity(loginActivityIntent);
        } else {
            Intent onboardingActivityIntent = new Intent(this, OnboardingActivity.class);
            finish();
            startActivity(onboardingActivityIntent);
        }

    }

    /**
     * Returns true if onboarding has occurred on this device before
     * @return true if onboarding has occurred on this device before
     */
    private boolean hasRegistered() {
        return sharedPref.getBoolean(Constants.ONBOARDING_FINISHED, false);
    }

    /**
     * Returns true if the user has an access token available on the device.
     * Note that this does not necessarily mean that the access token is valid.
     * @return true if the user has an access token available on the device.
     */
    private boolean hasLoggedIn() {
        return sharedPref.contains(Constants.ACCESS_TOKEN_KEY);
    }

}
