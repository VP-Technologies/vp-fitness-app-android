package com.example.neil.vp_fitness_frontend;

import android.support.v4.app.Fragment;
import android.view.View;

import java.util.HashMap;

/**
 * A superclass for onboarding fragment to implement from
 * @author Aaron Vontell
 */
public abstract class OnboardingFragment extends Fragment {

    /**
     * Method to trigger the binding of views
     * @param cont The container to find the views within
     */
    public abstract void bindViews(View cont);

    /**
     * Returns true if the inputs within this fragment are valid
     */
    public abstract boolean inputReady();

    /**
     * Returns the data which can be uploaded during the onboarding process
     * @return the data which can be uploaded during the onboarding process
     */
    public abstract HashMap<String, String> getData();

}
