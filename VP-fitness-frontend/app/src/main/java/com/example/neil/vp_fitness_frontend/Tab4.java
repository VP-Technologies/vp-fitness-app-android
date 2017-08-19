package com.example.neil.vp_fitness_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.avontell.fontutil.FontUtil;

import java.util.HashMap;

/**
 * Created by tejpatel on 7/26/17.
 */

public class Tab4 extends OnboardingFragment {

    /** Views to use throughout this fragment */
    private LinearLayout burnFatLinearLayout;
    private LinearLayout buildMuscleLinearLayout;
    private LinearLayout improveEnduranceLinearLayout;

    private int goal;
    private String opaque = "0.25";
    private String solid = "1";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab4, container, false);
        Typeface fancyFont = FontUtil.get("Raleway-Regular.ttf", this.getContext());
        FontUtil.overrideFonts(cont, fancyFont);
        bindViews(cont);
        return cont;
    }

    /**
     * Method to trigger binding of views
     * @param cont The container to find the views within
     */
    @Override
    public void bindViews(View cont) {

        this.burnFatLinearLayout = cont.findViewById(R.id.burn_fat_linear_layout);
        this.buildMuscleLinearLayout = cont.findViewById(R.id.build_muscle_linear_layout);
        this.improveEnduranceLinearLayout = cont.findViewById(R.id.improve_endurance_linear_layout);

        burnFatLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = 1;
                burnFatLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
                buildMuscleLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                improveEnduranceLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
            }
        });
        buildMuscleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = 2;
                burnFatLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                buildMuscleLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
                improveEnduranceLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
            }
        });
        improveEnduranceLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = 3;
                burnFatLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                buildMuscleLinearLayout.setAlpha(Float.valueOf(String.valueOf(opaque)));
                improveEnduranceLinearLayout.setAlpha(Float.valueOf(String.valueOf(solid)));
            }
        });

    }

    @Override
    public String ready() {

        if (goal > 0) {
            return null;
        } else {
            return getString(R.string.onboard_error_goal);
        }

    }

    /**
     * Returns the chosen goal data which can be uploaded during the onboarding process
     * @return the chosen goal data which can be uploaded during the onboarding process
     */
    @Override
    public HashMap<String, String> getData() {

        HashMap<String, String> results = new HashMap<>();
        results.put("goal", Integer.toString(goal));
        return results;

    }

}
