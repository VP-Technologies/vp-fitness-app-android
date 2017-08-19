package com.example.neil.vp_fitness_frontend.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.neil.vp_fitness_frontend.R;

/**
 * A class used to authenticate the user and hold user authentication info
 * @author Aaron Vontell
 */
public class Authentication {

    public static final String PRIVATE_KEY_HEADER =
            "Basic dnBmaXR3ZWJhcHA6SkJVWTlWRTY5MjQzQllDOTAyNDM4N0hHVlkzQVFGSw==";
    public static final String GRANT_TYPE = "password";
    public static final String ACCESS_TOKEN_KEY = "VP_ACCESS_TOKEN";

    public static String getAccessToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        return sharedPref.getString(ACCESS_TOKEN_KEY, "");
    }

    public static void saveAccessToken(Context context, String accessToken) {

        SharedPreferences sharedPref = context.getSharedPreferences(
                Constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(ACCESS_TOKEN_KEY, accessToken);
        editor.commit();
    }

}
