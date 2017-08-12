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
    private ImageButton forwardArrowButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab10, container, false);
        Typeface fancyFont = FontUtil.get("Raleway-Regular.ttf", this.getContext());
        FontUtil.overrideFonts(cont, fancyFont);
        return cont;
    }

    @Override
    public void bindViews(View cont) {

        this.usernameEditText = (MaterialEditText) cont.findViewById(R.id.username_edit_text);
        this.forwardArrowButton = (ImageButton) cont.findViewById(R.id.forward_arrow_button);
        forwardArrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean inputReady() {
        return false;
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> results = new HashMap<>();
        results.put("username", usernameEditText.getText().toString());
        return null;
    }
}
