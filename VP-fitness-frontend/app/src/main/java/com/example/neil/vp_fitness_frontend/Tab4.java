package com.example.neil.vp_fitness_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.neil.vp_fitness_frontend.utils.FontCache;

/**
 * Created by tejpatel on 7/26/17.
 */

public class Tab4 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab4, container, false);
        Typeface fancyFont = FontCache.get("Raleway-Regular.ttf", this.getContext());
        FontCache.overrideFonts(this.getContext(), cont, fancyFont);
        return cont;
    }
}
