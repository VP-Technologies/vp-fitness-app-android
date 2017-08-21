package com.vptech.fitness.app.onboarding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.avontell.fontutil.FontUtil;
import com.vptech.fitness.app.R;
import com.vptech.fitness.app.onboarding.OnboardingFragment;

import java.util.HashMap;

/**
 * A tab for selecting your desired equipment
 * @author Tej Patel
 * @author Neil Patel
 * @author Aaron Vontell
 */
public class Tab8 extends OnboardingFragment {

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

        CheckBox dumbellsCheckBox = cont.findViewById(R.id.dumbells_checkbox);
        CheckBox medicineBallCheckBox = cont.findViewById(R.id.medicine_ball_checkbox);
        CheckBox treadmillCheckBox = cont.findViewById(R.id.treadmill_checkbox);
        CheckBox benchPressCheckBox = cont.findViewById(R.id.bench_press_checkbox);
        CheckBox stairMachineCheckBox = cont.findViewById(R.id.stair_machine_checkbox);
        CheckBox ellipticalCheckBox = cont.findViewById(R.id.elliptical_checkbox);
        this.boxes = new CheckBox[] {dumbellsCheckBox, medicineBallCheckBox, treadmillCheckBox,
                benchPressCheckBox, stairMachineCheckBox, ellipticalCheckBox};

        // Set the listener for the "check all" button
        TextView checkAll = cont.findViewById(R.id.select_all_equipment);
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (CheckBox box : boxes) {
                    box.setChecked(true);
                }
            }
        });

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
