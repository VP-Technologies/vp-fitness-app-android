package com.vptech.fitness.app.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * A class used to authenticate the user and hold user authentication info
 * @author Aaron Vontell
 */
public class Authentication {

    public static final String PRIVATE_KEY_HEADER =
            "Basic dnBmaXR3ZWJhcHA6SkJVWTlWRTY5MjQzQllDOTAyNDM4N0hHVlkzQVFGSw==";
    public static final String GRANT_TYPE = "password";

    /**
     * Returns an access token that has been previously saved
     * @param context the context asking for this access token
     * @return an access token that has been previously saved
     */
    public static String getAccessToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        return sharedPref.getString(Constants.ACCESS_TOKEN_KEY, "");
    }

    /**
     * Saves the given access token onto the device for later use
     * @param context the context asking to save this access token
     * @param accessToken the access token to save on the device
     */
    public static void saveAccessToken(Context context, String accessToken) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.ACCESS_TOKEN_KEY, accessToken);
        editor.commit();
    }

    /**
     * Saves the fact that onboarding has occurred
     * @param context the context asking to save this information
     */
    public static void saveOnboardingFinished(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Constants.ONBOARDING_FINISHED, true);
        editor.commit();
    }

}
