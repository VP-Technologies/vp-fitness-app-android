package com.vptech.fitness.app.onboarding;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;

/**
 * A superclass for an onboarding fragment to implement from
 * @author Aaron Vontell
 */
public abstract class OnboardingFragment extends Fragment {

    /**
     * Method to trigger the binding of views
     * @param cont The container to find the views within
     */
    public abstract void bindViews(View cont);

    /**
     * Returns a string to display an error if the information has not been
     * filled out correctly. Otherwise, returns null.
     */
    public abstract String ready();

    /**
     * Returns the data which can be uploaded during the onboarding process
     * @return the data which can be uploaded during the onboarding process
     */
    public abstract HashMap<String, String> getData();

}
