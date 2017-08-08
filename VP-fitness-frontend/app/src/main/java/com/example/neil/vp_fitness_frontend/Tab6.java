package com.example.neil.vp_fitness_frontend;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.avontell.fontutil.FontUtil;

import java.util.HashMap;


public class Tab6 extends OnboardingFragment {

    /** Views to use throughout this fragment */
    private LinearLayout beginnerLinearLayout;
    private LinearLayout intermediateLinearLayout;
    private LinearLayout expertLinearLayout;

    private int difficulty;
    private String opaque = "0.25";
    private String solid = "1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab6, container, false);
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

        this.beginnerLinearLayout = (LinearLayout) cont.findViewById(R.id.beginner_linear_layout);
        this.intermediateLinearLayout = (LinearLayout) cont.findViewById(R.id.intermediate_linear_layout);
        this.expertLinearLayout = (LinearLayout) cont.findViewById(R.id.expert_linear_layout);

        beginnerLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = 1;
                beginnerLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
                intermediateLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                expertLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
            }
        });
        intermediateLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = 2;
                beginnerLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                intermediateLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
                expertLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
            }
        });
        expertLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficulty = 3;
                beginnerLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                intermediateLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                expertLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
            }
        });

    }

    @Override
    public boolean inputReady() {
        return false;
    }

    /**
     * Returns the chosen difficulty data which can be uploaded during the onboarding process
     * @return the chosen difficulty data which can be uploaded during the onboarding process
     */
    @Override
    public HashMap<String, String> getData() {

        HashMap<String, String> results = new HashMap<>();
        results.put("difficulty", Integer.toString(difficulty));
        return results;

    }
}
