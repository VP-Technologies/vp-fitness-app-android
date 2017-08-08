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

import com.avontell.fontutil.FontUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Tab8 extends OnboardingFragment {

    private CheckBox dumbellsCheckBox;
    private CheckBox medicineBallCheckBox;
    private CheckBox treadmillCheckBox;
    private CheckBox benchPressCheckBox;
    private CheckBox stairMachineCheckBox;
    private CheckBox ellipticalCheckBox;

    private ArrayList<Integer> equipmentArrayList = new ArrayList<>();

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

        this.dumbellsCheckBox = (CheckBox) cont.findViewById(R.id.dumbells_checkbox);
        this.medicineBallCheckBox = (CheckBox) cont.findViewById(R.id.medicine_ball_checkbox);
        this.treadmillCheckBox = (CheckBox) cont.findViewById(R.id.treadmill_checkbox);
        this.benchPressCheckBox = (CheckBox) cont.findViewById(R.id.bench_press_checkbox);
        this.stairMachineCheckBox = (CheckBox) cont.findViewById(R.id.stair_machine_checkbox);
        this.ellipticalCheckBox = (CheckBox) cont.findViewById(R.id.elliptical_checkbox);

    }

    @Override
    public boolean inputReady() {
        return false;
    }

    @Override
    public HashMap<String, String> getData() {
        return null;
    }
}
