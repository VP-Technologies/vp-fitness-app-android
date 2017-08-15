package com.example.neil.vp_fitness_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avontell.fontutil.FontUtil;
import com.vptechnologies.vpfancyslider.FancySlider;

import java.util.HashMap;

public class Tab5 extends OnboardingFragment {

    /** Views to use throughout this fragment */
    private FancySlider yearSlider;
    private FancySlider weightSlider;
    private FancySlider heightSlider;

    private TextView yearTextView;
    private TextView weightTextView;
    private TextView heightOneTextView;
    private TextView heightTwoTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab5, container, false);
        Typeface fancyFont = FontUtil.get("Raleway-Regular.ttf", this.getContext());
        FontUtil.overrideFonts(cont, fancyFont);

        bindViews(cont);

        return cont;
    }

    /**
     * Method to trigger the binding of views
     * @param cont The container to find the views within
     */
    @Override
    public void bindViews(View cont) {

        yearSlider = (FancySlider) cont.findViewById(R.id.yearBornSlider);
        weightSlider = (FancySlider) cont.findViewById(R.id.weightSlider);
        heightSlider = (FancySlider) cont.findViewById(R.id.heightSlider);

        yearTextView = (TextView) cont.findViewById(R.id.yearTextView);
        weightTextView = (TextView) cont.findViewById(R.id.weightTextView);
        heightOneTextView = (TextView) cont.findViewById(R.id.heightOneTextView);
        heightTwoTextView = (TextView) cont.findViewById(R.id.heightTwoTextView);

        // Attach the listeners to the sliders
        yearSlider.setOnValueChangedListener(new FancySlider.OnValueChangedListener() {
            @Override
            public void onValueChanged(float v) {
                yearTextView.setText(String.format("%s", (int) v));
            }
        });

        weightSlider.setOnValueChangedListener(new FancySlider.OnValueChangedListener() {
            @Override
            public void onValueChanged(float v) {
                weightTextView.setText(String.format("%s", (int) v));
            }
        });

        heightSlider.setOnValueChangedListener(new FancySlider.OnValueChangedListener() {
            @Override
            public void onValueChanged(float v) {
                heightOneTextView.setText(String.format("%s", (int) v / 12));
                heightTwoTextView.setText(String.format("%s", (int) v % 12));
            }
        });

    }

    /**
     * Returns true if the inputs within this fragment are valid
     */
    @Override
    public boolean inputReady() {
        return false;
    }

    /**
     * Returns the data which can be uploaded during the onboarding process
     * @return the data which can be uploaded during the onboarding process
     */
    @Override
    public HashMap<String, String> getData() {

        // Use the getValue method to get info from sliders
        HashMap<String, String> results = new HashMap<>();
        results.put("age", Float.toString(yearSlider.getValue()));
        results.put("weight", Float.toString(weightSlider.getValue()));
        results.put("height", Float.toString(heightSlider.getValue()));

        return null;
    }
}
