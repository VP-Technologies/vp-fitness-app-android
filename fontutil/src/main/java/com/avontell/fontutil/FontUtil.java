package com.avontell.fontutil;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Hashtable;

/**
 * Handles the changing of fonts to new font families and typefaces
 * @author Aaron Vontell
 * @version 0.0.1
 */
public class FontUtil {

    /**
     * A cache of fonts that may be used
     */
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    /**
     * Returns a font from the assets folder with the given name
     * @param name The font (including extension).For example, "font.ttf"
     * @param context The context asking for this font
     * @return The font corresponding to that font name
     */
    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }

    /**
     * Changes all TextView fonts in view 'v' to the given typeface 't'
     * @param v The view (even ViewGroup) asking for the font change
     * @param t The typeface that you would like to use
     */
    public static void overrideFonts(final View v, final Typeface t) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, t);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(t);
            }
        } catch (Exception e) {}
    }

}
