package com.vptech.fitness.app;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avontell.fontutil.FontUtil;

import java.util.HashMap;

// Fragment class for Tab 1 of onboarding screen
public class Tab1 extends OnboardingFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab1, container, false);
        Typeface fancyFont = FontUtil.get("Raleway-Regular.ttf", this.getContext());
        FontUtil.overrideFonts(cont, fancyFont);
        return cont;
    }

    /**
     * Method to trigger the binding of views
     * @param cont The container to find the views within
     */
    @Override
    public void bindViews(View cont) {}

    /**
     * Returns a string to display an error if the information has not been
     * filled out correctly. Otherwise, returns null.
     */
    @Override
    public String ready() {
        return null;
    }

    /**
     * Returns the data which can be uploaded during the onboarding process
     * @return the data which can be uploaded during the onboarding process
     */
    @Override
    public HashMap<String, String> getData() {
        return new HashMap<>();
    }
}
