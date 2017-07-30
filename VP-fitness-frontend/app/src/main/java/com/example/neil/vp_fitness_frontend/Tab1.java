package com.example.neil.vp_fitness_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neil.vp_fitness_frontend.utils.FontCache;

// Fragment class for Tab 1 of onboarding screen
public class Tab1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab1, container, false);
        Typeface fancyFont = FontCache.get("Raleway-Regular.ttf", this.getContext());
        FontCache.overrideFonts(this.getContext(), cont, fancyFont);
        return cont;
    }
}
