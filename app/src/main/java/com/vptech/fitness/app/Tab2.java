package com.vptech.fitness.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.avontell.fontutil.FontUtil;
import java.util.HashMap;

/**
 * An introduction tab to the fitness application
 * @author Tej Patel
 * @author Neil Patel
 * @author Aaron Vontell
 */
public class Tab2 extends OnboardingFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab2, container, false);
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

        final Activity context = getActivity();
        cont.findViewById(R.id.onboard_login_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginActivityIntent = new Intent(context, LoginActivity.class);
                context.finish();
                startActivity(loginActivityIntent);
            }
        });

    }

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
