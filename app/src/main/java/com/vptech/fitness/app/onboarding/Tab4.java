package com.vptech.fitness.app.onboarding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.avontell.fontutil.FontUtil;
import com.vptech.fitness.app.R;
import com.vptech.fitness.app.onboarding.OnboardingFragment;

import java.util.HashMap;

/**
 * A tab for selecting your overall goal of using the application
 * @author Tej Patel
 * @author Neil Patel
 * @author Aaron Vontell
 */
public class Tab4 extends OnboardingFragment {

    /** Views to use throughout this fragment */
    private LinearLayout burnFatLinearLayout;
    private LinearLayout buildMuscleLinearLayout;
    private LinearLayout improveEnduranceLinearLayout;

    private int goal;
    private final float OPAQUE = 0.25f;
    private final float SOLID = 1f;

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
                burnFatLinearLayout.setAlpha(SOLID);
                buildMuscleLinearLayout.setAlpha(OPAQUE);
                improveEnduranceLinearLayout.setAlpha(OPAQUE);
            }
        });
        buildMuscleLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = 2;
                burnFatLinearLayout.setAlpha(OPAQUE);
                buildMuscleLinearLayout.setAlpha(SOLID);
                improveEnduranceLinearLayout.setAlpha(OPAQUE);
            }
        });
        improveEnduranceLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal = 3;
                burnFatLinearLayout.setAlpha(OPAQUE);
                buildMuscleLinearLayout.setAlpha(OPAQUE);
                improveEnduranceLinearLayout.setAlpha(SOLID);
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
