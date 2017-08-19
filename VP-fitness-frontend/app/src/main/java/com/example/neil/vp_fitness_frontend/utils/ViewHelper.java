package com.example.neil.vp_fitness_frontend.utils;

import android.content.Context;
import android.support.annotation.Nullable;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * A collection of static methods which assist in manipulating and displaying
 * information or views.
 * @author Aaron Vontell
 */
public class ViewHelper {

    /**
     * Creates a MaterialDialog given the necessary items. show() is not called
     * @param context The calling context that requests this dialog
     * @param title The title of the dialog
     * @param content The content to display within the dialog
     * @param posText The text for the positive button
     * @param negText The text for the negative button (nullable)
     * @param posListener The action listener for the positive button (nullable)
     * @param negListener The action listener for the negative button (nullable)
     * @return the dialog builder for the desired dialog
     */
    public static MaterialDialog.Builder createDialog
            (Context context,
             String title,
             String content,
             String posText,
             @Nullable String negText,
             @Nullable MaterialDialog.SingleButtonCallback posListener,
             @Nullable MaterialDialog.SingleButtonCallback negListener) {

        // Attach the essential information
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText(posText);

        // Attach negative text and listeners, if indicated
        if (negText != null) {
            builder.negativeText(negText);
        }
        if (posListener != null) {
            builder.onPositive(posListener);
        }
        if (negListener != null) {
            builder.onNegative(negListener);
        }

        // Build, show, and return the material dialog
        return builder;

    }

}
