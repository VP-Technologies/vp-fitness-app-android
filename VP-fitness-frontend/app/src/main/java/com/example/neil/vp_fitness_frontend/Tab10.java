package com.example.neil.vp_fitness_frontend;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.avontell.fontutil.FontUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;


public class Tab10 extends OnboardingFragment {

    private MaterialEditText usernameEditText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab10, container, false);
        Typeface fancyFont = FontUtil.get("Raleway-Regular.ttf", this.getContext());
        FontUtil.overrideFonts(cont, fancyFont);
        bindViews(cont);
        return cont;
    }

    @Override
    public void bindViews(View cont) {
        this.usernameEditText = cont.findViewById(R.id.username_edit_text);
    }

    @Override
    public String ready() {

        //TODO: Check for whitespace

        if (usernameEditText.getText().toString().trim().length() < 3) {
            return getString(R.string.onboard_error_username);
        } else {
            return null;
        }

    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> results = new HashMap<>();
        results.put("username", usernameEditText.getText().toString());
        return results;
    }
}
