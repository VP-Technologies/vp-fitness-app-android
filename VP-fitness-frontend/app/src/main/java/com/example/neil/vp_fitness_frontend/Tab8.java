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
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.avontell.fontutil.FontUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Tab8 extends OnboardingFragment {

    private CheckBox dumbellsCheckBox;
    private CheckBox medicineBallCheckBox;
    private CheckBox treadmillCheckBox;
    private CheckBox benchPressCheckBox;
    private CheckBox stairMachineCheckBox;
    private CheckBox ellipticalCheckBox;
    private CheckBox[] boxes;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab8, container, false);
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

        this.dumbellsCheckBox = cont.findViewById(R.id.dumbells_checkbox);
        this.medicineBallCheckBox = cont.findViewById(R.id.medicine_ball_checkbox);
        this.treadmillCheckBox = cont.findViewById(R.id.treadmill_checkbox);
        this.benchPressCheckBox = cont.findViewById(R.id.bench_press_checkbox);
        this.stairMachineCheckBox = cont.findViewById(R.id.stair_machine_checkbox);
        this.ellipticalCheckBox = cont.findViewById(R.id.elliptical_checkbox);
        this.boxes = new CheckBox[] {this.dumbellsCheckBox, this.medicineBallCheckBox, this.treadmillCheckBox,
                this.benchPressCheckBox, this.stairMachineCheckBox, this.ellipticalCheckBox};

    }

    @Override
    public String ready() {
        return null;
    }

    @Override
    public HashMap<String, String> getData() {
        HashMap<String, String> results = new HashMap<>();
        String result = "{";
        for (int i = 0; i < boxes.length; i++) {
            CheckBox box = boxes[i];
            result += box.isChecked() ? i + "," : "";
        }
        result = result.endsWith(",") ? result.substring(0,result.lastIndexOf(",")) : result;
        results.put("equipment", result + "}");
        return results;
    }
}
