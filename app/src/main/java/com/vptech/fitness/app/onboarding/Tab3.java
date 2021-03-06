package com.vptech.fitness.app.onboarding;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.avontell.fontutil.FontUtil;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vptech.fitness.app.R;
import com.vptech.fitness.app.onboarding.OnboardingFragment;

import java.util.HashMap;

/**
 * A tab during onboarding for account creation
 * @author Tej Patel
 * @author Neil Patel
 * @author Aaron Vontell
 */
public class Tab3 extends OnboardingFragment {

    /** Views to use throughout this fragment */
    private MaterialEditText nameView;
    private MaterialEditText emailView;
    private MaterialEditText passwordView;
    private MaterialEditText passwordConfirmView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View cont = inflater.inflate(R.layout.tab3, container, false);
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
        this.nameView = cont.findViewById(R.id.full_name_edit_text);
        this.emailView = cont.findViewById(R.id.email_edit_text);
        this.passwordView = cont.findViewById(R.id.password_edit_text);
        this.passwordConfirmView = cont.findViewById(R.id.password_confirm_edit_text);
    }

    /**
     * Returns true if the inputs within this fragment are valid
     */
    @Override
    public String ready() {

        boolean nameGood = this.nameView.getEditableText().toString().length() > 2;
        // TODO: USE AN EMAIL REGEX
        boolean emailGood = this.emailView.getEditableText().toString().length() > 2;
        boolean passwordGood = this.passwordView.getEditableText().toString().length() > 6;
        boolean passwordConfirmGood = this.passwordConfirmView.getEditableText().toString().equals(
                this.passwordView.getEditableText().toString());

        if (!nameGood) {
            return getString(R.string.onboard_error_name);
        }
        else if (!emailGood) {
            return getString(R.string.onboard_error_email);
        }
        else if (!passwordGood) {
            return getString(R.string.onboard_error_password);
        }
        else if (!passwordConfirmGood) {
            return getString(R.string.onboard_error_match);
        } else {
            return null;
        }

    }

    /**
     * Returns the data which can be uploaded during the onboarding process
     * @return the data which can be uploaded during the onboarding process
     */
    @Override
    public HashMap<String, String> getData() {

        HashMap<String, String> results = new HashMap<>();
        results.put("name", this.nameView.getEditableText().toString());
        results.put("email", this.emailView.getEditableText().toString());
        results.put("password", this.passwordView.getEditableText().toString());
        return results;

    }

}
